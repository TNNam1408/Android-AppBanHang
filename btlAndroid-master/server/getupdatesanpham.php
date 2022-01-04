<?php
	include"connection.php";
	$tensanpham = $_POST['tensanpham'];
	$gia = $_POST['gia'];
	$hinhanhsanpham = $_POST['hinhanhsanpham'];
	$motasanpham = $_POST['motasanpham'];
	$idsanpham = $_POST['idsanpham'];

	if(strlen($tensanpham)> 0 && strlen($gia) >0 && strlen($hinhanhsanpham)> 0&& strlen($motasanpham)> 0&& strlen($idsanpham)> 0){
		$query = "INSERT INTO sanpham(id,tensanpham,gia,hinhanhsanpham,motasanpham,idsanpham) VALUES(null,'$tensanpham','$gia','$hinhanhsanpham','$motasanpham','$idsanpham')";
		if(mysqli_query($conn, $query)){
			$idsanpham = $conn->insert_id;
			echo $idsanpham;
			echo "Thêm sản phẩm thành công";
		}else{
			echo"Thất bại";
		}
	}else{
		echo "Hãy kiểm tra lại dữ liệu";
	}
?>