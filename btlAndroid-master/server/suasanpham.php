<?php
	include "connection.php";
	$id = $_POST['id'];
	$tensanpham = $_POST['tensanpham'];
	$gia = $_POST['gia'];
	$hinhanhsanpham = $_POST['hinhanhsanpham'];
	$motasanpham = $_POST['motasanpham'];
	$idsanpham = $_POST['idsanpham'];


	if(strlen($id) > 0 ){
		$checkuser = "SELECT *FROM sanpham WHERE ID='$id'";
		$result = mysqli_query($conn,$checkuser);

		if(mysqli_num_rows($result) < 1){
			echo "K có sản phẩm này";
		}else{
			if(strlen($tensanpham)> 0 && strlen($gia) >0 && strlen($hinhanhsanpham)> 0&& strlen($motasanpham)> 0&& strlen($idsanpham) >0 ){
				$query = "UPDATE sanpham SET tensanpham='$tensanpham', gia = '$gia', hinhanhsanpham = '$hinhanhsanpham',motasanpham='$motasanpham',idsanpham='$idsanpham'WHERE id = '$id' ";
				if(mysqli_query($conn, $query)){
					echo "Sửa thông tin sản phẩm thành công";
				}else{
					echo"Thấy bại";
				}
			}else{
				echo "Bạn hãy kiểm tra lại dữ liệu";
			}
		}
	}else{
		echo"Vui lòng nhập id sản phẩm cần sửa";
	}
?>
