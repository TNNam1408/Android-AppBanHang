
<?php
	include "connection.php";
	$id = $_POST['id'];
	
	if(strlen($id) > 0 ){
		$checkuser = "SELECT *FROM sanpham WHERE ID='$id'";
		$result = mysqli_query($conn,$checkuser);

		if(mysqli_num_rows($result) < 1){
			echo "K có sản phẩm này";
		}else{
			$query = "DELETE FROM sanpham WHERE id = '$id' ";
			if(mysqli_query($conn, $query)){
				
				echo "Xóa thành công ";
				echo $id;
			}else{
				echo"Thất bại";
			}
		}
	}else{
		echo "Hãy kiểm tra lại id";
	}
?>