$(document)
		.ready(
				function() {
					var contextRoot = getContextPath();
					registerNewUser = function() {
						var sendToSend = JSON
								.stringify(serializeObject($('#newCustomer')));
						if ($('#inputPassword').val() == $(
								'#inputVerifypassword').val()) {
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
												window.location
														.replace(contextRoot
																+ '/addUserDetails');
											} else if (xhr.status == "409") {
												$('#errorsUsername').html("");
												$('#errorsPassord').html("");
												$('#errorsVerifypassword')
														.html("");
												$('#errorsUsername')
														.append(
																"Account already existed. Please enter other username");
												make_visible('errorsUsername');
											} else if (xhr.status == "400") {
												var json = JSON
														.parse(xhr.responseText);
												$('#errorsUsername').html("");
												$('#errorsPassord').html("");
												$('#errorsVerifypassword')
														.html("");
												if (json
														.hasOwnProperty('username')) {
													$('#errorsUsername')
															.append(
																	json['username']);
													make_visible('errorsUsername');
												}
												if (json
														.hasOwnProperty('password')) {
													$('#errorsPassord').append(
															json['password']);
													make_visible('errorsPassord');
												}
											}
										}
									});
						} else {
							$('#errorsUsername').html("");
							$('#errorsPassord').html("");
							$('#errorsVerifypassword').html("");
							$('#errorsVerifypassword').append(
									"Mishmatch password and verify password");
							make_visible('errorsVerifypassword');
						}
					};
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