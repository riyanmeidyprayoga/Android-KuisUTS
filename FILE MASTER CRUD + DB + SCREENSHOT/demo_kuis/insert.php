<?php
require_once 'koneksi.php';
if($_SERVER['REQUEST_METHOD'] == 'POST')
{
$nim = $_POST['nim'];
$nik = $_POST['nik'];
$nama = $_POST['nama'];
$alamat = $_POST['alamat'];
$no = $_POST['no'];

$query = "INSERT INTO kuis (nim, nik, nama, alamat, no) VALUES
('$nim', '$nik', '$nama', '$alamat', '$no')";
$exeQuery = mysqli_query($konek, $query);
echo ($exeQuery) ? json_encode(array('kode' =>1, 'pesan' => 'berhasil menambahkan data'))
: json_encode(array('kode' =>2, 'pesan' => 'data gagal ditambahkan'));
}
else
{
	echo json_encode(array('kode' =>101, 'pesan' => 'request tidak valid'));
}
?>
