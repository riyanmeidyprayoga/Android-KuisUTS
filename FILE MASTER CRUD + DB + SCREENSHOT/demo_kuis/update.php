<?php
require_once 'koneksi.php';
if($_SERVER['REQUEST_METHOD'] == 'POST')
{
$id = $_POST['id'];
$nim = $_POST['nim'];
$nik = $_POST['nik'];
$nama = $_POST['nama'];
$alamat = $_POST['alamat'];
$no = $_POST['no'];

$query = "UPDATE kuis SET nim = '$nim' ,nik = '$nik', nama = '$nama', alamat = '$alamat', no = '$no'
WHERE id='$id'";
$exeQuery = mysqli_query($konek, $query);
echo ($exeQuery) ? json_encode(array('kode' =>1, 'pesan' => 'data berhasil update')) :
json_encode(array('kode' =>2, 'pesan' => 'data gagal diupdate'));
}
else
{
echo json_encode(array('kode' =>101, 'pesan' => 'request tidak valid'));
}
?>