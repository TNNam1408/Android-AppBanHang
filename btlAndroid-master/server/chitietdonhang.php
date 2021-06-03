<?php
	include"connection.php";
	$json = $_POST['json'];
	$data = json_decode($json, true);
	foreach ($data as $value) {
		$madonhang = $value['madonhang'];
		$masanpham = $value['masanpham'];
		$tensanpham = $value['tensanpham'];
		$giasanpham = $value['giasanpham'];
		$soluongsanpham = $value['soluongsanpham'];
		$query = "INSERT INTO chitietdonhang(id,madonhang,masanpham,tensanpham,giasanpham,soluongsanpham) VALUES(null,'$madonhang','$masanpham','$tensanpham','$giasanpham','$soluongsanpham')";
		$Dxta = mysqli_query($conn,$query);

	}
	if($Dxta){
		echo"1";
	}else{
		echo"0";
	}
?>