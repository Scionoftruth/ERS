let userid;

let verifyLoggedIn = async () => {
	let res = await fetch('http://localhost:8080/ERS/api/getSession');
	let obj = await res.json();
	console.log(obj);
	
	if(obj.userid < 0){
		location.href = "../html/index.html";
	}
	else{
		userId = obj.id;
	}
}

document.getElementById("logout").addEventListener('click', async () => {
	let res = await fetch('http://localhost:8080/ERS/api/logout');
	location.href = "http://localhost:8080/ERS/";
});

document.getElementById("home").addEventListener('click', () => {
	location.href = "resources/html/manager-dashboard.html";
});

/* Reimbursement functions */

let populateTable = (list) => {
	
	let table = document.getElementById("re-table");
	
	table.innerHTML = '<tr><th>Status</th><th>Type</th><th>Amount</th><th>Empolyee</th><th>Submitted Date</th><th>Approve/Deny</th></tr>';
	
	list.forEach((obj)=> {
		let index = 1;
		
		let row=table.insertRow(index++);
		row.id = obj.reId;
		let status = row.insertCell(0);
		status.innerHTML = obj.status.reimbursement_status;
		let type = row.insertCell(1);
		type.innerHTML = obj.type.reimbursement_type;
		let amount = row.insertCell(2);
		amount.innerHTML = Number (obj.amount).toFixed(2);
		let employee = row.insertCell(3);
		employee.innerHTML = obj.userConnection.username;
		let subDate = row.insertCell(4);
		subDate.innerHTML = new Date(obj.submitteddate).toDateString();
		let appDen = row.insertCell(5);
		appDen.innerHTML = `<button onclick="approve(${obj.id})" id="appr">Approve</button> <button onclick="deny(${obj.id})" id="deny">Deny</button>`;
		let descRow = table.insertRow(index++);
		let desc = descRow.insertCell(0);
		desc.setAttribute("colspan", "6");
		desc.className = "desc";
		desc.innerHTML = `<h3>Description:</h3><p>${obj.description}</p>`;
		
	});

}

let getPendingReimbursements = async () => {
	let res = await fetch(`http://localhost:8080/ERS/api/getAllPending`);
	let obj = await res.json();
	console.log(obj);
	return obj;
}

let approve = async (id) => {
	
	await verifyLoggedIn();
	let rese = await fetch(`http://localhost:8080/ERS/api/getUser?userid=${userId}`);
	let user = await rese.json();
	console.log(user);
	
	console.log(`Approved this reimbursement ${id}`);
	
	let date = new Date().toLocaleDateString();
	let manager = user.id;
	
	obj = {
		userid : manager,
		reid : id,
		date: date
	}
	
	console.log(date);
	
	let res = await fetch(`http://localhost:8080/ERS/api/approveReimbursement`,
	{
		method: 'POST',
		headers: {
      		'Content-Type': 'application/json'
    	},
    	body: JSON.stringify(obj)
	});
	
	let list = await getPendingReimbursements();
	populateTable(list);
}

let deny = async (id) => {
	
	await verifyLoggedIn();
	let rese = await fetch(`http://localhost:8080/ERS/api/getUser?userid=${userId}`);
	let user = await rese.json();
	console.log(user);
	
	console.log(`Deny this reimbursement ${id}`);
	
	let date = new Date().toLocaleDateString();
	let manager = user.id;
	
	obj = {
		userid : manager,
		reid : id,
		date: date
	}
	
	console.log(date);
	
	let res = await fetch(`http://localhost:8080/ERS/api/denyReimbursement`,
	{
		method: 'POST',
		headers: {
      		'Content-Type': 'application/json'
    	},
    	body: JSON.stringify(obj)
	});
	
	let list = await getPendingReimbursements();
	populateTable(list);
	
}

let init = async () => {
	await verifyLoggedIn();
	console.log(userId);
	let res = await getPendingReimbursements();
	populateTable(res);
}

init();