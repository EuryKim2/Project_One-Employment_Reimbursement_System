let table_body = document.querySelector("#reimb > tbody");


window.onload = function(){
	getSessUser();
	load_tickets();
}


function getSessUser(){
	let xhttp = new XMLHttpRequest();
	
	xhttp.onreadystatechange = function(){
		if(xhttp.readyState == 4 && xhttp.status==200){
			let user = JSON.parse(xhttp.responseText);
			
		}
	}
	xhttp.open("GET", "http://localhost:8080/ProjectOne/getsessionuser.json");
	xhttp.send();

}

function load_tickets(){
	let xhttp = new XMLHttpRequest();
	xhttp.open("GET", "http://localhost:8080/ProjectOne/getusertickets.json")
	xhttp.onload=()=>{
		let tickets = JSON.parse(xhttp.responseText);
		create_table(tickets);
	}
	xhttp.send();
}

function create_table(tickets){
	while (table_body.firstChild){
		table_body.removeChild(table_body.firstChild);
	}
	tickets.forEach((row)=>{
		let tr = document.createElement("tr");
		for(var temp in row){
			let td = document.createElement("td");
			if(temp=="status"){
				switch(row[temp]){
					case 1:
					td.textContent = "Pending"
					break;
					case 2:
					td.textContent = "Approved"
					break;
					case 3:
					td.textContent = "Denied"
					break;
				}
			}else if(temp=="type"){
				switch(row[temp]){
					case 1:
					td.textContent = "Lodging"
					break;
					case 2:
					td.textContent = "Travel"
					break;
					case 3:
					td.textContent = "Food"
					break;
					case 4:
					td.textContent = "Other"
					break;
				}
			}else{
				td.textContent = row[temp];
			}
			tr.appendChild(td);
		}
		table_body.appendChild(tr);
	})
}
