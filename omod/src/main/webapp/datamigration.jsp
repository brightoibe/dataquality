<% ui.decorateWith("appui", "standardEmrPage") %>

<h1 align="center"> <b>MIGRATION SUMMARY</b></h1>

<% ui.includeJavascript("nigeriaemr", "datatables.min.js") %>

${ ui.includeFragment("datamigration", "users") }