$(function () {
  /*  getUrl("retrievePassword/selectorMode");*/
    $("#get").click(function () {
       /* var email = $("#email").val();*/
    	var original = $(this).val();
    	var email = '15121875276@163.com';
        //alert(email);
        if(email==null||email==""){
            alertBox("请输入邮箱地址","");
        }else{
            $("#get").attr("disabled", "disabled");
            $(this).val("发送中...");
            ajaxGetCodeToEmail(email,this,original);

        }
    });
    $("#sub").click(function () {
        var code = $("#code").val();
        var email = $("#email").val();
        if(email==null||email==""){
            alertBox("请输入邮箱地址","");
        }else if(code==null||code==""){
            alertBox("请输入验证码","");
        }else{
            $("#sub").attr("disabled", "disabled");
            ajaxValidateCodeOfEmail(code);
        }

    });
});

/**
 * 发送邮箱验证码
 * @param email用户邮箱
 */
function ajaxGetCodeToEmail(email,that,original) {
    
        var url = "ajaxGetCodeToEmail.do";
       
        $.post(
            url,
            {'email': email},
            function (data) {
                if (data == '1') {
                    alert("发送成功,请注意查收");
                    $(that).removeAttr("disabled");
                    $(that).val(original);
                } else {
                    alert("您输入的邮箱未注册！","");
                }
                //倒计时
                settime(that);
            })
    
}

/**
 * 邮箱方式重置密码验证码校验
 * @param code    用户输入验证码
 */
function ajaxValidateCodeOfEmail(code) {
    if (/^[A-Za-z0-9]{6}$/.test(code)) {
        var url = "/Lvscore_User_M/ajaxValidateCodeOfEmail";
        $.post(
            url,
            {'code': code},
            function (data) {
                $("#sub").removeAttr("disabled", "disabled");
                if (data == 1) {
                    window.location.href = "/";
                } else {
                    alertBox("验证码输入错误","");
                }
            });
    } else {
        $("#sub").removeAttr("disabled", "disabled");
        alertBox("验证码输入错误","");
    }
}