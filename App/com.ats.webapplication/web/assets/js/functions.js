function deleteEmployee(id) {
    $.post(`deleteEmployee`, {id}, (data) => {
        location.reload(true);
    });
}

function deleteTask(id) {
    $.post(`deleteTask`, {id}, (data) => {
        location.reload(true);
    });
}

function deleteJob(id) {
    $.post(`deleteJob`, {id}, (data) => {
        location.reload(true);
    });
}