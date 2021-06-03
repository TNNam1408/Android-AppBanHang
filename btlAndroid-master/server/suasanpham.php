<?php
	include "connection.php";
	$id = $_POST['id'];
	$tensanpham = $_POST['tensanpham'];
	$gia = $_POST['gia'];
	$hinhanhsanpham = $_POST['hinhanhsanpham'];
	$motasanpham = $_POST['motasanpham'];
	$idsanpham = $_POST['idsanpham'];

	if(strlen($tensanpham)> 0 && strlen($gia) >0 && strlen($hinhanhsanpham)> 0&& strlen($motasanpham)> 0&& strlen($idsanpham) >0 ){
		$query = "UPDATE sanpham SET tensanpham='$tensanpham', gia = '$gia', hinhanhsanpham = '$hinhanhsanpham',motasanpham='$motasanpham',idsanpham='$idsanpham'WHERE id = '$id' ";

		if(mysqli_query($conn, $query)){
			echo "1";
		}else{
			echo"That bai";
		}
	}else{
		echo "Ban hay kiem tra lai du lieu";
	}
?>
