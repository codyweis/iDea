<?php
session_start();
	if($_SERVER['REQUEST_METHOD'] == 'POST'){
		
		require_once('connect.inc.php');
		$content = $_POST['content'];
		$user = $_POST['username'];
		$topic_title = $_POST['topic'];
		
		$sql = "INSERT INTO IDEA(user, topic_title, content, post_date) VALUES ('$user', '$topic_title', '$content', NOW())";
		
		if(mysqli_query($conn, $sql)){
			echo "created";
		}else{
			echo "Could not create post";
		}
	}else{
		echo 'error';
	}	

		mysqli_close($conn);

?>
