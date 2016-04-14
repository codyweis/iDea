<?php
	if($_SERVER['REQUEST_METHOD'] == 'POST'){
		
		require_once('connect.inc.php');
		$post_id= $_POST['id'];
		
		$sql = "UPDATE IDEA SET likes = likes + 1 WHERE id = '$post_id'";
		
		if(mysqli_query($conn, $sql)){
			echo "success";
		}else{
			echo "Could not create post";
		}
	}else{
		echo 'error';
	}	

		mysqli_close($conn);

?>