   <?php
   try {  
   require 'DB_Manager.php'; 
   
   $address = $_REQUEST["addresse"]; 
   $numOfParking = $_REQUEST["numOfParking"]; 
   $sql = "INSERT INTO `student_table`(  `address`, `numOfParking`)
   VALUES ( '$address', '$numOfParking')";
   if ($conn->query($sql) === TRUE) {  
   $last_id = $conn->insert_id;  
   echo $last_id; 
   }  
   else 
   {   echo "Error: " . $sql . "\n" . $conn->error; 
 } 
 }  catch(Exception $e) { 
 echo "Exception Error See Log....";
 error_log($e->getMessage() , 0); 
 } 
 $conn->close();
 ?> 