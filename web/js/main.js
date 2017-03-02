/**
 * Ajax request to things
 */
function refresh() {
  $.get("http://127.0.0.1:8080/lights/1/", function(data) {
      $("#light-status").html("ON");
    }).fail(function(data) {
      $("#light-status").html("OFF");
    });
}
