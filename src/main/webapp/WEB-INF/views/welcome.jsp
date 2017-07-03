<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="tr">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<META http-equiv=content-type content=text/html;charset=windows-1254>

<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">

<title>Welcome</title>

<link href="${contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet">

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body
	style="background-image: url('${contextPath}/resources/images/sky-clouds-white-blue-full-hd-background-nature.jpg');">


	<div class="container" style="text-align: right; font-style: italic;">

		<c:if test="${pageContext.request.userPrincipal.name != null}">
			<form id="logoutForm" method="POST" action="${contextPath}/logout">
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
			</form>

			<h2>
				<font color="white">Welcome</Font> <font color="white">${user.nameSurname}
					|</Font> <a onclick="document.forms['logoutForm'].submit()">Logout</a>

			</h2>
			
		</c:if>

	</div>
	<div style="margin-left: 100px;">

		<form action="${contextPath}/getCityTemp/" method="get">
			<select name="sehir" style="width: 270px; height: 40px;">
				<option>---&#304l se&#231iniz...---</option>
				<option value="Adana">Adana</option>
				<option value="Adıyaman">Ad&#305yaman</option>
				<option value="Afyonkarahisar">Afyonkarahisar</option>
				<option value="Ağrı">A&#287r&#305</option>
				<option value="Aksaray">Aksaray</option>
				<option value="Amasya">Amasya</option>
				<option value="Ankara">Ankara</option>
				<option value="Antalya">Antalya</option>
				<option value="Ardahan">Ardahan</option>
				<option value="Artvin">Artvin</option>
				<option value="Aydın">Ayd&#305n</option>
				<option value="Balıkesir">Bal&#305kesir</option>
				<option value="Bartın">Bart&#305n</option>
				<option value="Batman">Batman</option>
				<option value="Bayburt">Bayburt</option>
				<option value="Bilecik">Bilecik</option>
				<option value="Bingöl">Bing&#246l</option>
				<option value="Bitlis">Bitlis</option>
				<option value="Bolu">Bolu</option>
				<option value="Burdur">Burdur</option>
				<option value="Bursa">Bursa</option>
				<option value="Çanakkale">&#199anakkale</option>
				<option value="Çankırı">&#199ank&#305r&#305</option>
				<option value="Çorum">&#199orum</option>
				<option value="Denizli">Denizli</option>
				<option value="Diyarbakır">Diyarbak&#305r</option>
				<option value="Düzce">D&#252zce</option>
				<option value="Edirne">Edirne</option>
				<option value="Elazığ">Elaz&#305&#287</option>
				<option value="Erzincan">Erzincan</option>
				<option value="Erzurum">Erzurum</option>
				<option value="Eskisehir">Eski&#351ehir</option>
				<option value="Gaziantep">Gaziantep</option>
				<option value="Giresun">Giresun</option>
				<option value="Gümüşhane">G&#252m&#252&#351hane</option>
				<option value="Hakkari">Hakkari</option>
				<option value="Hatay">Hatay</option>
				<option value="Iğdır">I&#287d&#305r</option>
				<option value="Isparta">Isparta</option>
				<option value="İstanbul">&#304stanbul</option>
				<option value="İzmir">&#304zmir</option>
				<option value="Kahramanmaraş">Kahramanmara&#351</option>
				<option value="Karabük">Karab&#252k</option>
				<option value="Karaman">Karaman</option>
				<option value="Kars">Kars</option>
				<option value="Kastamonu">Kastamonu</option>
				<option value="Kayseri">Kayseri</option>
				<option value="Kırıkkale">K&#305r&#305kkale</option>
				<option value="Kırklareli">K&#305rklareli</option>
				<option value="Kırsehir">K&#305r&#351ehir</option>
				<option value="Kilis">Kilis</option>
				<option value="Kocaeli">Kocaeli</option>
				<option value="Konya">Konya</option>
				<option value="Kütahya">K&#252tahya</option>
				<option value="Malatya">Malatya</option>
				<option value="Manisa">Manisa</option>
				<option value="Mardin">Mardin</option>
				<option value="Mersin">Mersin</option>
				<option value="Muğla">Mu&#287la</option>
				<option value="Muş">Mu&#351</option>
				<option value="Nevşehir">Nev&#351ehir</option>
				<option value="Niğde">Ni&#287de</option>
				<option value="Ordu">Ordu</option>
				<option value="Osmaniye">Osmaniye</option>
				<option value="Rize">Rize</option>
				<option value="Sakarya">Sakarya</option>
				<option value="Samsun">Samsun</option>
				<option value="Siirt">Siirt</option>
				<option value="Sinop">Sinop</option>
				<option value="Sivas">Sivas</option>
				<option value="Şanlıurfa">&#350anl&#305urfa</option>
				<option value="Şırnak">&#350&#305rnak</option>
				<option value="Tekirdağ">Tekirda&#287</option>
				<option value="Tokat">Tokat</option>
				<option value="Trabzon">Trabzon</option>
				<option value="Tunceli">Tunceli</option>
				<option value="Uşak">U&#351ak</option>
				<option value="Van">Van</option>
				<option value="Yalova">Yalova</option>
				<option value="Yozgat">Yozgat</option>
				<option value="Zonguldak">Zonguldak</option>
			</select> <input type="submit" value="G&#246nder">

		</form>

	</div>
	<div align="center">
				<font color="red" style="font-size: x-large;"> ${city} </Font><font color="white" style="font-size: x-large;"> ${weather_temp} </Font> <br>				
				<font color="white" style="font-size: x-large;"> ${weather_humidity} </Font> <br>
				<font color="white" style="font-size: x-large;"> ${city_time} </Font>
			</div>
	<!-- /container -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="api.openweathermap.org/data/2.5/forecast?zip=Istanbul,TR"></script>
	<script
		src="http://samples.openweathermap.org/data/2.5/forecast?id=524901&appid=1004f69aa9506063496f7891597238be"></script>
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
