<?php
if($_SERVER['REQUEST_METHOD']=='POST'){
		$kode_hewan = $_POST['kode_hewan'];
		$nama_hewan = $_POST['nama_hewan'];
		$kategori_hewan = $_POST['kategori_hewan'];
		
		
		if($kode_hewan == '' || $nama_hewan == '' || $kategori_hewan == '' ){
			echo 'Mohon datanya dilengkapi terlebih dahulu!';
		}else{
			require_once('koneksi.php');
			$sql = "SELECT * FROM tb_hewan WHERE kode_hewan='$kode_hewan'";
			
			$check = mysqli_fetch_array(mysqli_query($con,$sql));
			
			if(isset($check)){
				echo 'Nama  sudah ada';
			}else{				
				$sql = "INSERT INTO tb_hewan (kode_hewan, nama_hewan, kategori_hewan) VALUES('$kode_hewan','$nama_hewan','$kategori_hewan')";
				if(mysqli_query($con,$sql)){
					echo 'Data berhasil diinput';
				}else{
					echo 'oops! Mohon coba lagi!';
				}
			}
			mysqli_close($con);
		}
}else{
echo 'error';
}