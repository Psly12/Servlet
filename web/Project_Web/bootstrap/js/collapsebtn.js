$('#details').on('shown.bs.collapse', function () {
    $('#more').html('<span class=\"glyphicon glyphicon-chevron-up\"></span>Hide Details');     
});
$('#details').on('hidden.bs.collapse', function () {
    $('#more').html('<span class=\"glyphicon glyphicon-chevron-down\"></span>Show Details');     
});


