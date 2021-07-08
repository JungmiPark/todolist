<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <link href="style.css" rel="stylesheet" type="text/css" />
    <title>TO DO LIST</title>
    
  </head>
  <body>
    <div id="container">
      <section id="header-section">
       <h1>My To Do List</h1>
       <a href="todoForm" id="add-btn" class="button">ADD To Do</a>
      </section>      
      <section id="todo-section" class="list">
        <h2>TODO</h2>
	    <ul id="todo-section-ul">
	    </ul>
	  </section>
      <section id="doing-section" class="list">
        <h2>DOING</h2>
        <ul id="doing-section-ul">
	    </ul>
      </section>
      <section id="done-section" class="list">
      	<h2>DONE</h2>
        <ul id="done-section-ul">
	    </ul>
      </section>
    </div>
    <!-- todo list 불러오기 -->
    <script>
      function getList (list, sectionId) {
    	  for (idx in list){
        	  var element = document.querySelector(sectionId + " > ul");
        	  element.innerHTML += "<div id='"+list[idx].type+ "-" + list[idx].id+"-li'><li><h4>"+ list[idx].title + "</h4>" 
        	  + "<p>등록날짜: "+ list[idx].regdate.substr(0,10).replace(/-/g, '.') + ", " + list[idx].name +", 우선순위 " + list[idx].sequence + "</p></li></div>"
        	  if (sectionId != "#done-section"){
        		  element.innerHTML += "<span><button id="+list[idx].type+ "-" + list[idx].id +" class='type-update-btn' onclick='clickEvent(this.id)'> → </button></span>";
	        	  document.getElementById(list[idx].type+ "-" + list[idx].id+"-li").getElementsByTagName("li")[0].appendChild(document.getElementById(list[idx].type+ "-" + list[idx].id));
        	  }
          }
      }
	  
      var todoList = ${todoList}
      getList(todoList, "#todo-section");
      
      var doingList = ${doingList };
      getList(doingList, "#doing-section");
      
      var doneList = ${doneList };
      getList(doneList, "#done-section");
       	
    </script>
    <!-- type 수정 -->
   	<script>
	  function ajax(prior, data) {
	  	var oReq = new XMLHttpRequest();   
	   	oReq.open("POST", "main");
	   	oReq.setRequestHeader("Content-Type", "application/json");
	   	oReq.onreadystatechange = function () {
	   	    if (oReq.readyState == 4 && oReq.status == 200){
	   	    	var section = document.getElementById(data.type+"-section-ul");
	   	    	var content = document.getElementById(prior+'-'+data.id+"-li");
	   	    	section.appendChild(content);	   	        
	   	        if(data.type === "done"){
	   	        	//버튼 삭제
	   	        	content.remove(content.getElementsByTagName("button"));
	   	        } else{
	   	        	// 버튼 id 변경
	   	        	content.getElementsByTagName("button").id = data.type+'-'+data.id+"-li";
	   	        }
	   	    } 
	   	 }
	   	 oReq.send(JSON.stringify(data));
	   };
	  
	  function clickEvent(text) {
		  var tmp = text.split("-");
		  switch (tmp[0]){
		  case "todo":
			  var data = {type : "doing", id: tmp[1] }
			  break;
		  case "doing":
			  var data = {type : "done", id: tmp[1] }
			  break;
		  }
		  ajax(tmp[0], data)
	  }
	  
   	</script>
</body>
</html>