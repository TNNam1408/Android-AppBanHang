<?php
	require 'connection.php';
	class Product{
		function Product($id, $tensp, $giasp, $hinhanhsp, $motasp, $idsp)
		{
			$this->id = $id;
			$this->tenloaisanpham = $tenloaisanpham;
			$this->hinhanhloaisanpham = $hinhanhloaisanpham;
		}
	}

	$sql = "SELECT * FROM loaisanpham ORDER BY id DESC LIMIT 6";
	$data = mysqli_query($conn,$sql);
	$arrProducts = array();

	while ($row = mysqli_fetch_assoc($data)) {
	    array_push($arrProducts, new Product(
	    	$row['id'],
	    	$row['tenloaisanpham'],
	    	$row['hinhanhloaisanpham']));
	}
	echo json_encode($arrProducts);
 ?>