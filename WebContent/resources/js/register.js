$(document)
		.ready(
				function() {
					var contextRoot = getContextPath();
					var isValidAll;

					registerNewUser = function() {
						isValidAll = true;
						verifyUsername();
						verifyPassword();
						verifyMatchPassword();
						if (!isValidAll)
							return false;

						var sendToSend = JSON.stringify(serializeObject($('#newUser')));
						$
								.ajax({
									url : contextRoot + '/register',
									type : 'POST',
									dataType : 'json',
									data : sendToSend,
									contentType : 'application/json',
									success : function(response) {
									},
									error : function(xhr, exception) {
										console.log(xhr);
										make_hidden('errorsUsername');
										make_hidden('errorsPassord');
										make_hidden('errorsVerifypassword');
										if (xhr.status == "200") {
											window.location.replace(contextRoot
													+ '/addUserDetails');
										} else if (xhr.status == "409") {
											$('#errorsUsername').html("");
											$('#errorsPassord').html("");
											$('#errorsVerifypassword').html("");
											$('#errorsUsername')
													.append(
															"Account already existed. Please enter other username");
											make_visible('errorsUsername');
										} else if (xhr.status == "400") {
											var json = JSON
													.parse(xhr.responseText);
											$('#errorsUsername').html("");
											$('#errorsPassord').html("");
											$('#errorsVerifypassword').html("");
											if (json.hasOwnProperty('username')) {
												$('#errorsUsername').append(
														json['username']);
												make_visible('errorsUsername');
											}

											if (json.hasOwnProperty('password')) {
												$('#errorsPassord').append(
														json['password']);
												make_visible('errorsPassord');
											}
										}
									}
								});
					};

					function verifyMatchPassword() {
						if ($('#inputPassword').val() != $('#inputVerifypassword').val()) {
							isValidAll = false;
							$('#errorsUsername').html("");
							$('#errorsPassord').html("");
							$('#errorsVerifypassword').html("");
							$('#errorsVerifypassword').append(
									"Mishmatch password and verify password");
							make_visible('errorsVerifypassword');
						}
					}

					function verifyPassword() {
						var re = new RegExp("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[a-zA-Z0-9]{6,}$");

						if ($("#inputPassword").val() == "") {
							isValidAll = false;
							$("#errorsPassord").text(
									"This field should not be empty");
							$("#errorsPassord").show();
						} else if (!re.test($("#inputPassword").val())) {
							isValidAll = false;
							$("#errorsPassord")
									.text(
											"At least 6 letters and one capital, small letter, number");
							$("#errorsPassord").show();
						} else {
							$("#errorsPassord").hide();
						}
					}

					function verifyUsername() {
						if ($("#inputUsername").val() == "") {
							isValidAll = false;
							$("#errorsUsername").text(
									"This field should not be empty");
							$("#errorsUsername").show();
						} else {
							$("#errorsUsername").hide();
						}
					}

					make_hidden = function(id) {
						var element = document.getElementById(id);
						element.style.display = 'none';
					};

					make_visible = function(id) {
						var element = document.getElementById(id);
						element.style.display = 'block';
					};

					function serializeObject(form) {
						var jsonObject = {};
						var array = form.serializeArray();
						$.each(array, function() {
							jsonObject[this.name] = this.value;
						});
						return jsonObject;
					}
					;

					function getContextPath() {
						return window.location.pathname.substring(0,
								window.location.pathname.indexOf("/", 2));
					}
					;

					function signin() {
						window.location.replace(contextRoot + '/login');
					}
				});