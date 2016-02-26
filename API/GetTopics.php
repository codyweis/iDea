<?php

	if($_SERVER['REQUEST_METHOD'] == 'GET'){
		
		$sql = "SELECT * FROM TOPIC";
		
		require_once('connect.inc.php');
		
		$run = mysqli_query($conn, $sql);
		$result = array();
		
		while($row = mysqli_fetch_array($run)){
			array_push($result, array(
				'id'=>$row['id'],
				'title'=>$row['title']
			));
		}
		
		echo json_encode(array('result'=>$result));
		
		mysqli_close($conn);
	}
?>