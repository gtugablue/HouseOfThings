/**
 * Ajax request to things
 */
function refresh() {
  $.get("http://localhost:3000/lights/1/", function(data) {
      $("#light-status").html("ON");
      $("#off").hide();
      $("#on").show();
    }).fail(function(data) {
      $("#light-status").html("OFF");
      $("#on").hide();
      $("#off").show();
    });
}
