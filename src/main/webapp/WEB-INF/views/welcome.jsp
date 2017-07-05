<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<META http-equiv=content-type content=text/html;charset=utf-8>

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
	style="background-image: url('${contextPath}/resources/images/editedpicturetwo.png');">


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
				<option value="Adiyaman">Ad&#305yaman</option>
				<option value="Afyonkarahisar">Afyonkarahisar</option>
				<option value="Agrı">A&#287r&#305</option>
				<option value="Aksaray">Aksaray</option>
				<option value="Amasya">Amasya</option>
				<option value="Ankara">Ankara</option>
				<option value="Antalya">Antalya</option>
				<option value="Ardahan">Ardahan</option>
				<option value="Artvin">Artvin</option>
				<option value="Aydin">Ayd&#305n</option>
				<option value="Balikesir">Bal&#305kesir</option>
				<option value="Bartin">Bart&#305n</option>
				<option value="Batman">Batman</option>
				<option value="Bayburt">Bayburt</option>
				<option value="Bilecik">Bilecik</option>
				<option value="Bingol">Bing&#246l</option>
				<option value="Bitlis">Bitlis</option>
				<option value="Bolu">Bolu</option>
				<option value="Burdur">Burdur</option>
				<option value="Bursa">Bursa</option>
				<option value="Canakkale">&#199anakkale</option>
				<option value="Cankırı">&#199ank&#305r&#305</option>
				<option value="Corum">&#199orum</option>
				<option value="Denizli">Denizli</option>
				<option value="Diyarbakir">Diyarbak&#305r</option>
				<option value="Duzce">D&#252zce</option>
				<option value="Edirne">Edirne</option>
				<option value="Elazıg">Elaz&#305&#287</option>
				<option value="Erzincan">Erzincan</option>
				<option value="Erzurum">Erzurum</option>
				<option value="Eskisehir">Eski&#351ehir</option>
				<option value="Gaziantep">Gaziantep</option>
				<option value="Giresun">Giresun</option>
				<option value="Gumushane">G&#252m&#252&#351hane</option>
				<option value="Hakkari">Hakkari</option>
				<option value="Hatay">Hatay</option>
				<option value="Igdır">I&#287d&#305r</option>
				<option value="Isparta">Isparta</option>
				<option value="Istanbul">&#304stanbul</option>
				<option value="Izmir">&#304zmir</option>
				<option value="Kahramanmaras">Kahramanmara&#351</option>
				<option value="Karabuk">Karab&#252k</option>
				<option value="Karaman">Karaman</option>
				<option value="Kars">Kars</option>
				<option value="Kastamonu">Kastamonu</option>
				<option value="Kayseri">Kayseri</option>
				<option value="Kirikkale">K&#305r&#305kkale</option>
				<option value="Kirklareli">K&#305rklareli</option>
				<option value="Kirsehir">K&#305r&#351ehir</option>
				<option value="Kilis">Kilis</option>
				<option value="Kocaeli">Kocaeli</option>
				<option value="Konya">Konya</option>
				<option value="Kutahya">K&#252tahya</option>
				<option value="Malatya">Malatya</option>
				<option value="Manisa">Manisa</option>
				<option value="Mardin">Mardin</option>
				<option value="Mersin">Mersin</option>
				<option value="Mugla">Mu&#287la</option>
				<option value="Mus">Mu&#351</option>
				<option value="Nevsehir">Nev&#351ehir</option>
				<option value="Nigde">Ni&#287de</option>
				<option value="Ordu">Ordu</option>
				<option value="Osmaniye">Osmaniye</option>
				<option value="Rize">Rize</option>
				<option value="Sakarya">Sakarya</option>
				<option value="Samsun">Samsun</option>
				<option value="Siirt">Siirt</option>
				<option value="Sinop">Sinop</option>
				<option value="Sivas">Sivas</option>
				<option value="Sanlıurfa">&#350anl&#305urfa</option>
				<option value="Sırnak">&#350&#305rnak</option>
				<option value="Tekirdag">Tekirda&#287</option>
				<option value="Tokat">Tokat</option>
				<option value="Trabzon">Trabzon</option>
				<option value="Tunceli">Tunceli</option>
				<option value="Usak">U&#351ak</option>
				<option value="Van">Van</option>
				<option value="Yalova">Yalova</option>
				<option value="Yozgat">Yozgat</option>
				<option value="Zonguldak">Zonguldak</option>
			</select> <input type="submit" value="G&#246r&#252nt&#252le">

		</form>

	</div>



	<div align="center">
<table style="width: 50%;">

<tr>

<th>
<p style="text-align: center;">
<font color="red" style="font-size: 40px;">
${part1.city}</font>
<br>
<font color="black" style="font-size: small;">
${part1.getDate()} </Font>
<br>
<img src="${imageurl}"> <font color="blue"
style="font-size: 30px;"> ${Temp_text} </Font>
<br>
<font color="blue" style="font-size: medium;">${part1.weatherDescription}</Font>
<br>
<font color="blue" style="font-size: medium;">${humidity_text}
</Font>
</p> <br>

</th>


</tr>
<tr>
<td><font color="black" style="font-size: x-large;">Saatlik Hava Durumu</td>

</tr>
<tr>
<td colspan=6><table>

<TR>

<TD><font color="white" style="font-size: x-large;"> ${part2List.get(0).temp} </Font> <font color="white" style="font-size: x-large;"> ${Celcius} <img src= "${part2List.get(0).url}" ><br><font color="black" style="font-size: x-large;"> ${part2List.get(0).time} </Font> </TD>
<TD><font color="white" style="font-size: x-large;"> ${part2List.get(1).temp} </Font>  <font color="white" style="font-size: x-large;"> ${Celcius} <img src= "${part2List.get(1).url}" ><br><font color="black" style="font-size: x-large;"> ${part2List.get(1).time} </Font></TD>
<TD><font color="white" style="font-size: x-large;"> ${part2List.get(2).temp} </Font>  <font color="white" style="font-size: x-large;"> ${Celcius} <img src= "${part2List.get(2).url}" ><br><font color="black" style="font-size: x-large;"> ${part2List.get(2).time} </Font></TD>
<TD><font color="white" style="font-size: x-large;"> ${part2List.get(3).temp} </Font>  <font color="white" style="font-size: x-large;"> ${Celcius}<img src= "${part2List.get(3).url}" ><br><font color="black" style="font-size: x-large;"> ${part2List.get(3).time} </Font></TD>
<TD><font color="white" style="font-size: x-large;"> ${part2List.get(4).temp} </Font>  <font color="white" style="font-size: x-large;"> ${Celcius}<img src= "${part2List.get(4).url}" ><br><font color="black" style="font-size: x-large;"> ${part2List.get(4).time} </Font></TD>
<TD><font color="white" style="font-size: x-large;"> ${part2List.get(5).temp} </Font>  <font color="white" style="font-size: x-large;"> ${Celcius} <img src= "${part2List.get(5).url}" ><br><font color="black" style="font-size: x-large;"> ${part2List.get(5).time} </Font></TD>

</TR>
</td>

</tr>

</table>
</table>
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
