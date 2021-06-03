<?php 
	require 'connection.php';

	$name = $_POST['tenkhachhang'];
	$phone = $_POST['sodienthoai'];
	$email = $_POST['email'];

	if (strlen($name) > 0 && strlen($phone) > 0 && strlen($email) > 0) {
		$sql = "INSERT INTO tb_customers(name, phone, email) VALUES ('$name','phone','email')";
		if (mysqli_query($conn, $sql)) {
			$order_id = $conn->insert_id;
			echo $order_id;
		}else {
			echo 'Fail';
		}
	}else {
		echo "Check input customer infomation.";
	}
 ?>