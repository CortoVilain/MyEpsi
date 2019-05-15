$(document).ready(function () {
                $("#ins_pwd1").on('keyup', function () {
                    if ($(this).val().length < 6) {
                        $('#mdpCourt').html('Mot de passe trop court, 6 caractère minimum !');
                        $("#ins_pwd2").prop('disabled', true);
                        $(this).css('border-color', 'red');
                    } else {
                        $(this).css('border-color', 'green');
                        $('#mdpCourt').html('');
                        $("#ins_pwd2").prop('disabled', false);
                        $("#ins_pwd2").keyup(function () {
                            if ($("#ins_pwd1").val() === $("#ins_pwd2").val()) {
                                $('#valMdp').html('');
                                $("#ins_pwd1").css('border-color', 'green');
                                $("#ins_pwd2").css('border-color', 'green');
                                $('#inscription').prop('disabled', false);
                            } else {
                                $('#valMdp').html('Mot de passe différent !');
                                $("#ins_pwd2").css('border-color', 'red');
                                $('#inscription').prop('disabled', true);
                            }
                        });
                    }
                });
            });