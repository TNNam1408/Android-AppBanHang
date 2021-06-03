<?php 
	require 'connection.php';
	class Product{
		function Product($id, $tensp, $giasp, $hinhanhsp, $motasp, $idsp)
		{
			$this->id = $id;
			$this->tensp = $tensp;
			$this->giasp = $giasp;
			$this->hinhanhsp = $hinhanhsp;
			$this->motasp = $motasp;
			$this->idsp = $idsp;
		}
	}

	$sql = "SELECT * FROM tb_products ORDER BY id DESC LIMIT 6";
	$data = mysqli_query($conn,$sql);
	$arrProducts = array();

	while ($row = mysqli_fetch_assoc($data)) {
	    array_push($arrProducts, new Product(
	    	$row['id'],
	    	$row['name'],
	    	$row['price'],
	    	$row['images'],
	    	$row['description'],
	    	$row['brand_id']));
	}
	echo json_encode($arrProducts);
 ?>