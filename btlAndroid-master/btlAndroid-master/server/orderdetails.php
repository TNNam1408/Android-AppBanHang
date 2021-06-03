<?php 
	require 'connection.php';

	$json = $_POST['json'];
	$data = json_decode($json,true);
	foreach ($data as $value) {
		$order_id = $value['madonhang'];
		$product_id = $value['masanpham'];
		$product_name = $value['tensanpham'];
		$product_price = $value['giasanpham'];
		$product_amount = $value['soluongsanpham'];

		$sql = "INSERT INTO tb_orderdetails(id, order_id, product_id, product_name, product_price, product_amount) VALUES (null,'$order_id','$product_id','$product_name','$product_price','$product_amount')";
		$result = mysqli_query($conn,$sql);
	}

	if ($result) {
		echo '1';
	}else {
		echo '0';
	}
 ?>