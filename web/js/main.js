/**
 * Ajax request to things
 */
function refresh() {
  $.get("http://localhost:3000/lights/1/", function(data) {
      $("#light-status").html("ON");
    }).fail(function(data) {
      $("#light-status").html("OFF");
    });
}
