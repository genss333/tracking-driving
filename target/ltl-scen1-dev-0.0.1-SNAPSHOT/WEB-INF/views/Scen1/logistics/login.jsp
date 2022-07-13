<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>Login</title>
<%@ include file="/include/homemapcss.jsp"%>
<%@ include file="/include/css.jsp"%>
</head>

	<body style="background-color: aliceblue;">
		<div class="container" style="margin-top:10%">
			<div class="row justify-content-center">
				<div class="col-md-9 col-lg-12 col-xl-10">
					<div class="card shadow-lg o-hidden border-0 my-5">
						<div class="card-body p-0">
							<div class="row">
								<div class="col-lg-6">
									<div class="p-5">
										<div class="text-center">
											<h4 class="text-dark mb-4">Login</h4>
										</div>
										<form class="user">
											<div class="form-group"><input class="form-control form-control-user" type="email" id="exampleInputEmail" aria-describedby="emailHelp" placeholder="Enter Email Address..." name="email"></div>
											<div class="form-group"><input class="form-control form-control-user" type="password" id="exampleInputPassword" placeholder="Password" name="password"></div>
											<div class="form-group">
												<div class="custom-control custom-checkbox small">
													<div class="form-check"><input class="form-check-input custom-control-input" type="checkbox" id="formCheck-1"><label class="form-check-label custom-control-label" for="formCheck-1">Remember Me</label></div>
												</div>
											</div><a class="btn btn-primary btn-block text-white btn-user" href= "${pageContext.request.contextPath}/provider" type="submit">Login</a>
											<hr><a class="btn btn-primary btn-block text-white btn-google btn-user" role="button"><i class="fab fa-google"></i>&nbsp; Login with Google</a><a class="btn btn-primary btn-block text-white btn-facebook btn-user" role="button"><i class="fab fa-facebook-f"></i>&nbsp; Login with Facebook</a>
											<hr>
										</form>
										<div class="text-center"><a class="small" href="${pageContext.request.contextPath}/provider/register">Create an Account!</a></div>
									</div>
								</div>
								<div class="col-lg-6 d-none d-lg-flex">
									<div class="flex-grow-1 bg-login-image" style="background-image: url(${pageContext.request.contextPath}/contents/img/loginimg.jpg);">
										<h4 style="text-align: center; color: white; margin-top: 10%;">LTL Provider Web Login</h4>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>


	<%@ include file="/include/js.jsp"%>
	<%@ include file="/include/homemapfunc.jsp"%>
</body>

</html>