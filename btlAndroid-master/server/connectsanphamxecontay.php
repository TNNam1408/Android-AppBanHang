<?php
Class Database{
	private $host='localhost';
	private $user='root';
	private $password='';
	private $dbname='xe';

	private $conn= null;
	private $result= null;

	public function connect(){
		error_reporting(0);
		$this->conn= mysqli_connect($this->host,$this->user,$this->password,$this->dbname);
		if(!$this->conn){
			echo "<p style='color: red'>Kết nối dữ liệu thất bại.</p>";
			exit();
		}
		mysqli_set_charset($this->conn,'UTF8');			
	}

	public function execute($sql){
		$this->result=mysqli_query($this->conn,$sql);		
		return $this->result;	
	}

	public	function Getinfo()
	{
		// $page = $_GET['page'];
		// $idsanpham = $_POST['idsanpham'];
		// $space = 5;
		// $limit = ($page - 1) * $space;
		// $mangsanpham = array();
		//  $this->execute("SELECT * FROM sanpham WHERE idsanpham = $idsanpham LIMIT $limit, $space");

		 $idsanpham = 2;
		 $this->execute("SELECT * FROM sanpham WHERE idsanpham = $idsanpham");
        if (mysqli_num_rows($this->result) == 0) 
        {
			return null;
        }
        else{		
			while ($datas = mysqli_fetch_assoc($this->result)) {
				$data[] = $datas;
			}  			
		}
		return $data;
	}
}
?>