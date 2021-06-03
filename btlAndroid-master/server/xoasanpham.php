<?php
	include "connection.php";
	$id = $_POST['id'];
	
	if(strlen($id) > 0 ){
		$query = "DELETE FROM sanpham WHERE id = '$id' ";

		if(mysqli_query($conn, $query)){
			echo "1";
		}else{
			echo"That bai";
		}
	}else{
		echo "Ban hay kiem tra lai du lieu"+'id';
	}
?>