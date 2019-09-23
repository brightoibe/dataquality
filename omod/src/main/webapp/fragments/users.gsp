<%
def id = config.id
%>
<%=ui.resourceLinks()%>


<style type="text/css">
    .dt-buttons{
    float: right;
    }
    #apps{
    margin-bottom: 60px;
    }

    .buttons-html5{
    text-decoration: none;
    margin-left: 5px;
    margin-bottom: 10px;
    text-align: center;
    border-radius: 3px;
    background: -webkit-gradient(linear, 50% 0%, 50% 100%, color-stop(0%, #5b57a6), color-stop(100%, #5b57a6));
    background: -webkit-linear-gradient(top, #5b57a6, #5b57a6);
    background: -moz-linear-gradient(top, #5b57a6, #5b57a6);
    background: -o-linear-gradient(top, #5b57a6, #5b57a6);
    background: -ms-linear-gradient(top, #5b57a6, #5b57a6);
    background: linear-gradient(top, #5b57a6, #5b57a6);
    background-color: #5b57a6;
    border: #5b57a6 1px solid;
    padding: 8px 20px;
    display: inline-block;
    line-height: 1.2em;
    color: white;
    cursor: pointer;
    min-width: 0;
    max-width: 300px;
    text-decoration: none;
    padding: 5px 6px;
    min-width: 70px;
    font-size: 0.9em;
    }

    #myTable_paginate{
    display: flex-inline;
    }
    #myTable_paginate li {
    margin:2px;
    padding:3px;
    text-decoration: none;
    text-align: center;
    border-radius: 3px;
    background: -webkit-gradient(linear, 50% 0%, 50% 100%, color-stop(0%, #5b57a6), color-stop(100%, #5b57a6));
    background: -webkit-linear-gradient(top, #5b57a6, #5b57a6);
    background: -moz-linear-gradient(top, #5b57a6, #5b57a6);
    background: -o-linear-gradient(top, #5b57a6, #5b57a6);
    background: -ms-linear-gradient(top, #5b57a6, #5b57a6);
    background: linear-gradient(top, #5b57a6, #5b57a6);
    background-color: #5b57a6;
    border: #5b57a6 1px solid;
    display: inline-block;
    color: white;
    cursor: pointer;
    width:auto;
    }

    #myTable_paginate li a{
    color:white;
    }
</style>



<script>
    jq = jQuery;
    jq(document).ready(function () {
    jq('#myTable').DataTable({
    dom: 'Bfrtip',
    buttons: [
    'copy', 'excel', 'pdf'
    ]
    });
    });

</script>


<!--<div id="apps" align="center">

    <a id="demoapp-homepageLink-demoapp-homepageLink-extension" href="#"
       class="button app big">

        <i class="icon-user"></i>
        <br>

        Total Pateints
        <p><b>${ui.format(totalPatients)}</b></p>
    </a>

    <a id="coreapps-activeVisitsHomepageLink-coreapps-activeVisitsHomepageLink-extension"
       href="#" class="button app big">

        <i class="icon-stethoscope"></i>
        <br>
        Lab Records
        <p><b>${ui.format(totallLaboratoryEncounter)}</b></p>
    </a>

    <a id="org-openmrs-module-coreapps-activeVisitsHomepageLink-org-openmrs-module-coreapps-activeVisitsHomepageLink-extension"
       href="#" class="button app big">

        <i class="icon-vitals"></i>
        <br>
        Pharmacy Records
        <p><b>${ui.format(totalPharmacyEncounter)}</b></p>
    </a>

    <a id="referenceapplication-registrationapp-registerPatient-homepageLink-referenceapplication-registrationapp-registerPatient-homepageLink-extension"
       href="#"
       class="button app big">

        <i class="icon-calendar"></i>
        <br>
        Care card Records
        <p><b>${ui.format(totalCareCardEncounter)}</b></p>
    </a>

</div>-->
<div class="container">
    <table id="myTable">
        <thead>
            <tr><th colspan='6' style='text-align: left; font-size: 16px;'>Clinical</th></tr>
        </thead>
        <thead style="font-size: 13px;">
            <tr>
                <th>S/N</th>
                <th>Indicator</th>
                <th>Numerator</th>
                <th>Denominator</th>
                <th>Performance</th>
                <th>Export</th>
            </tr>

        </thead>
        <tbody>

            <tr>
                <td>I</td>
                <td>Proportion of all active patients with a documented educational status </td>
                <td>${ui.format(activepatientseducationalstatuscount)}</td>
                <td>${ui.format(activepatientcount)}</td>
                <td>${ui.format(percentageeducationalstatus)}%</td>
                <td></td>
            </tr>
            <tr>
                <td>II</td>
                <td>Proportion of all active patients with a documented marital status </td>
                <td>${ui.format(activepatientsmaritalstatuscount)}</td>
                <td>${ui.format(activepatientcount)}</td>
                <td>${ui.format(percentagemaritalstatus)}%</td>
                <td></td>
            </tr>
            <tr>
                <td>III</td>
                <td>Proportion of all active patients with a documented occupational status </td>
                <td>${ui.format(activepatientsoccupationalstatuscount)}</td>
                <td>${ui.format(activepatientcount)}</td>
                <td>${ui.format(percentageoccupationalstatus)}%</td>
                <td></td>
            </tr>
            <tr>
                <td>IV</td>
                <td>Proportion of patients newly started on ART in the last 6 months with documented age and/or Date of Birth</td>
                <td>${ui.format(startedartlast6monthscountdocumenteddob)}</td>
                <td>${ui.format(startedartlast6monthscount)}</td>
                <td>${ui.format(percentagestartedartlast6monthswithdocumenteddob)}%</td>
                <td></td>
            </tr>
            <tr>
                <td>V</td>
                <td>Proportion of patients newly started on ART in the last 6 months with documented Sex</td>
                <td>${ui.format(startedartlast6monthscountdocumentedsex)}</td>
                <td>${ui.format(startedartlast6monthscount)}</td>
                <td>${ui.format(percentagestartedartlast6monthswithdocumentedsex)}%</td>
                <td></td>
            </tr>
            <tr>
                <td>VI</td>
                <td>Proportion of patients newly started on ART in the last 6 months with registered address/LGA of residence </td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td>VII</td>
                <td>Proportion of patients newly started on ART in the last 6 months with documented date of HIV diagnosis</td>
                <td>${ui.format(startedartlast6monthscountdocumenteddateconfirmedpositive)}</td>
                <td>${ui.format(startedartlast6monthscount)}</td>
                <td>${ui.format(percentagestartedartlast6monthswithdocumenteddateconfirmedpositive)}%</td>
                <td></td>
            </tr>
            <tr>
                <td>VIII</td>
                <td>Proportion of patients newly started on ART in the last 6 months with documented HIV enrollment date</td>
                <td>${ui.format(startedartlast6monthscountdocumentedenrollmentdate)}</td>
                <td>${ui.format(startedartlast6monthscount)}</td>
                <td>${ui.format(percentagestartedartlast6monthswithdocumenteddateenrollmentdate)}%</td>
                <td></td>
            </tr>
            <tr>
                <td>IX</td>
                <td> Number of All patients Newly started on ART in the last 6months with a documented HIV ART Start Date</td>
                <td>${ui.format(startedartlast6monthscountwithdrugpickup)}</td>
                <td>${ui.format(startedartlast6monthscount)}</td>
                <td>${ui.format(percentagestartedartlast6monthswithdocumenteddateenrollmentdate)}%</td>
                <td></td>
            </tr>
            <tr>
                <td>X</td>
                <td>Proportion of patients newly started on ART in the last 6 months with documented CD4 result </td>
                <td>${ui.format(startedartlast6monthscountwithcd4count)}</td>
                <td>${ui.format(startedartlast6monthscount)}</td>
                <td>${ui.format(percentagestartedartlast6monthswithcd4count)}%</td>
                <td></td>
            </tr>
            <tr>
                <td>XI</td>
                <td>Proportion of patients with a clinic visit in the last 6 months that had documented weight</td>
                <td>${ui.format(clinicvisitlast6monthswithdocumentedweight)}</td>
                <td>${ui.format(clinicvisitlast6monthscount)}</td>
                <td>${ui.format(percentagestclinicvisitlast6monthswithdocumentedweight)}%</td>
                <td></td>
            </tr>
            <!--
                <td></td>
                <td>${ui.format()}</td>
                <td>${ui.format()}</td>
                <td>${ui.format()}</td>
                <td><a href=''>Get List</a></td>
            -->
            <tr>
                <td>XII</td>
                <td>Proportion of pediatric patients with a clinic visit in the last 6 months that had documented MUAC </td>
                <td>${ui.format(pediatricclinicvisitlast6monthswithdocumentedmuac)}</td>
                <td>${ui.format(pediatricclinicvisitlast6monthscount)}</td>
                <td>${ui.format(percentagestclinicvisitlast6monthswithdocumentedmuac)}%</td>
                <td></td>
            </tr>
            <tr>
                <td>XIII</td>
                <td>Proportion of patients with a clinic visit in the last 6 months that had documented WHO clinical stage</td>
                <td>${ui.format(clinicvisitlast6monthswithdocumentedwho)}</td>
                <td>${ui.format(clinicvisitlast6monthscount)}</td>
                <td>${ui.format(percentagestclinicvisitlast6monthswithdocumentedwho)}%</td>
                <td></td>
            </tr>
            <tr>
                <td>XIV</td>
                <td>Proportion of patients with a clinic visit in the last 6 months that had documented TB status</td>
                <td>${ui.format(clinicvisitlast6monthswithdocumentedtbstatus)}</td>
                <td>${ui.format(clinicvisitlast6monthscount)}</td>
                <td>${ui.format(percentagestclinicvisitlast6monthswithdocumentedtbstatus)}%</td>
                <td></td>
            </tr>
            <tr>
                <th colspan='6'  style='text-align: left; font-size: 16px;'>Pharmacy</th>
            </tr> 
            <tr>
                <th>S/N</th>
                <th>Indicator</th>
                <th>Numerator</th>
                <th>Denominator</th>
                <th>Performance</th>
                <th>Export</th>
            </tr>
            <tr>
                <td>I</td> 
                <td>Proportion of patients with a documented ART regimen quantity in the last drug refill visit</td>
                <td>${ui.format(lastarvpickupwithquantitycohort)}</td>
                <td>${ui.format(lastarvpickupcohort)}</td>
                <td>${ui.format(percentagelastarvpickupwithquantity)}%</td>
                <td></td>    
            </tr>
            <tr>
                <td>II</td>
                <td>Proportion of patients with a documented ART regimen duration in the last drug refill visit</td>
                <td>${ui.format(lastarvpickupwithdurationcohort)}</td>
                <td>${ui.format(lastarvpickupcohort)}</td>
                <td>${ui.format(percentagelastarvpickupwithduration)}%</td>
                <td></td>    
            </tr>
            <tr>
                <td>III</td> 
                <td>Proportion of patients with documented ART regimen in the last drug refill visit</td>
                <td>${ui.format(lastarvpickupwithregimencohort)}</td>
                <td>${ui.format(lastarvpickupcohort)}</td>
                <td>${ui.format(percentagelastarvpickupwithregimen)}%</td>
                <td></td>    
            </tr>
            <tr>
                <td>IV</td> 
                <td>Proportion of patients with ART regimen duration more than six(6) months  in the last drug refill visit</td>
                <td>${ui.format(lastarvpickupwithregimendurationmorethan6monthscohort)}</td>
                <td>${ui.format(lastarvpickupcohort)}</td>
                <td>${ui.format(percentagelastarvpickupwithregimendurationmorethan6months)}%</td>
                <td></td>    
            </tr>

            <tr>
                <th colspan='6'  style='text-align: left; font-size: 16px;'>Laboratory</th>
            </tr> 
            <tr>
                <th>S/N</th>
                <th>Indicator</th>
                <th>Numerator</th>
                <th>Denominator</th>
                <th>Performance</th>
                <th>Export</th>
            </tr>
            <tr>
                <td>I</td>
                <td>Proportion of eligible patients with documented Viral Load results done in the last one year</td>
                <td>${ui.format(viralloadeligiblewithresultcohort)}</td>
                <td>${ui.format(viralloadeligible)}</td>
                <td>${ui.format(percentageeligiblewithdocumentedresult)}%</td>
                <td></td>       
            </tr>
            <tr>
                <td>II</td>
                <td>Proportion of patients with Viral Load result that had documented specimen collection date </td>
                <td>${ui.format(viralloadresultwithsamplecollection)}</td>
                <td>${ui.format(viralloadeligiblewithsamplecollection)}</td>
                <td>${ui.format(percentageeligiblewithsamplecollected)}%</td>
                <td></td>       
            </tr>
            <tr>
                <td>III</td> 
                <td>Proportion of patients with Viral Load result that had documented specimen sent date </td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>      
            </tr>
            <tr>
                <td>IV</td> 
                <td>Proportion of patients with Viral load results with a documented date sample was received at the PCR lab</td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>       
            </tr>


        </tbody>
    </table>
</div>
<!--<div class="container">

<table id="myTable">
<thead style="font-size: 13px;">
<tr>
    <th>PatientID</th>
    <th>Pateint Name</th>
    <th>Lab records</th>
    <th>Pharmacy Records</th>
    <th>ARTstartdate</th>
    <th>LastPickup</th>
    <th>firstDocumentedRegimen</th>
    <th>lastDocumentedRegimen</th>
</tr>
</thead>
<tbody>
<% if (patientLineList) { %>
<% patientLineList.each { %>
<tr>
    <td>${ui.format(it.PatientId)}</td>
    <td>${ui.format(it.PatientName)}</td>
    <td>${ui.format(it.countOfLabEncounter)}</td>
    <td>${ui.format(it.countOfPharmacyEncounter)}</td>
    <td>${ui.format(it.dateOfFirstEncounter)}</td>
    <td>${ui.format(it.dateOfLastEncounter)}</td>
    <td>${ui.format(it.firstDocumentedRegimen)}</td>
    <td>${ui.format(it.lastDocumentedRegimen)}</td>
</tr>
<% } %>
<% } else { %>
<tr>
    <td colspan="8" align="center">No Record To display</td>
</tr>
<% } %>
</tbody>
</table>
</div>-->











