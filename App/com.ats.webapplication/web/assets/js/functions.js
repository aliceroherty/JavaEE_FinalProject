function deleteEmployee(id) {
    $.post(`deleteEmployee`, {id}, (data) => {
        location.reload(true);
    });
}
function deleteTask(id) {

    $.post(`deleteTask`, {id}, (data) => {
        console.log(data);

        if (data === "-1") {
            alert("Cannot Delete, Task in use.");
        } else {
            location.reload(true);
        }

    });
}

function deleteJob(id) {
    $.post(`deleteJob`, {id}, (data) => {
        location.reload(true);
    });
}

function insertEmpTask(taskID, empID) {
    let empSkills = document.getElementById('empSkills');
    let url = window.location.href;
    empID = url.charAt(url.length-1)
    if (empSkills.selectedIndex !== -1) {
        
        taskID = empSkills.selectedOptions[0].value;
        $.post(`insertEmpTask`, {taskID: taskID, empID: empID}, (data) => {
            location.reload(true);
        });
    }
}

function deleteEmpTask(empID) {
    let skills = document.getElementById('skills');
    if (skills.selectedIndex !== -1) {
        let taskID = skills.selectedOptions[0].value;
        $.post(`deleteEmpTask`, {taskID: taskID, empID: empID}, (data) => {
            location.reload(true);
        });
    }
}
function deleteTeam(id) {
    $.post(`deleteTeam`, {id}, (data) => {
        location.reload(true);
    });
}