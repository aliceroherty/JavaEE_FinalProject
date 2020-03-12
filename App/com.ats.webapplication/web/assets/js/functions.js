function deleteEmployee(id) {
    $.post(`deleteEmployee`, {id}, (data) => {
        location.reload(true);
    });
}