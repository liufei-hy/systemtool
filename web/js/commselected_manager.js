/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function EnableSelectedFirst(divId){
    var aa = document.getElementById(divId);

    if (aa.options.length >= 2){
        aa.options[1].selected = true;
    }
}

function EnableSelectedAll(option, optionCmp){
    EnableSelectedFirst(option.projectNameDivId);
    EnableSelectedFirst(option.variantNameDivId);
    EnableSelectedFirst(option.versionDivId);
    EnableSelectedFirst(option.dataIndexDivId);

    EnableSelectedFirst(optionCmp.projectNameDivId);
    EnableSelectedFirst(optionCmp.variantNameDivId);
    EnableSelectedFirst(optionCmp.versionDivId);
    EnableSelectedFirst(optionCmp.dataIndexDivId);
}
            
function UpdateTestIndex(jsonResult, divId, divFirstName, isTestIndex, enableAvg){
    var indexRes = JSON.parse(jsonResult);
    var indexObject = document.getElementById(divId);

    //console.log(divId);
    indexObject.options.length = 0;
    indexObject.add(new Option(divFirstName, ""));

    if (isTestIndex && enableAvg){
        if (indexRes.length > 1){
            indexObject.add(new Option("AVG(*)", "*"));
        }
    }

    for (var i = 0; i < indexRes.length; i ++){
        var tmpVal="";
        if (isTestIndex){
            tmpVal = "Test" + indexRes[i];
        }else{
            tmpVal = indexRes[i];
        }                    
        indexObject.add(new Option(tmpVal, indexRes[i]));
    }
    
    if (indexObject.options.length >= 2){
        indexObject.options[1].selected = true;
        return true;
    }else{
        return false;
    }
}

function GetVariantNames(option){
    var projectName = $("#" + option.projectNameDivId).val();
    var dataUrl     = "TestDataGet?dataType=VariantName&indexType=" + option.indexType + "&projectname=" + projectName;

    $.ajax({
        type: "post",
        asyc: false,
        url: dataUrl,
        data: "{}",
        success: function(result){
            if (UpdateTestIndex(result, option.variantNameDivId, option.variantDefaultStr, false, false)){
                GetVersions(option);
            }
        }
    });
}

function GetVersions(option){
    var projectName = $("#" + option.projectNameDivId).val();
    var variantName = $("#" + option.variantNameDivId).val();
    var dataUrl     = "TestDataGet?dataType=Version&indexType=" + option.indexType + "&projectname=" 
                            + projectName + "&projectvariant=" + variantName;
    $.ajax({
        type: "post",
        asyc: false,
        url: dataUrl,
        data: "{}",
        success: function(result){
            //console.log(result);
            if (UpdateTestIndex(result, option.versionDivId, option.versionDefaultStr, false, false)){
                GetTestDataIndex(option);
            }
        }
    });
}

function GetProjectVariantVersionInfo(option){
    var projectName = $("#" + option.projectNameDivId).val();
    var variantName = $("#" + option.variantNameDivId).val();
    var version     = $("#" + option.versionDivId).val();
    
    return projectName + "_" + variantName + "_" + version;
}

function GetTestDataIndex(option){
    var projectName = $("#" + option.projectNameDivId).val();
    var variantName = $("#" + option.variantNameDivId).val();
    var version     = $("#" + option.versionDivId).val();

    if (typeof(eval(option.callback)) === "function"){
        //console.log("Has callback function");
        option.callback(projectName, variantName, version);
    }else{
        //console.log("Not callback function");
    }

    var dataUrl = "TestDataGet?dataType=DataIndex&indexType=" + option.indexType + "&projectname=" 
            + projectName + "&projectvariant=" + variantName + "&version=" + version;
    $.ajax({
       type: "post",
       asyc: false,
       url: dataUrl,
       data: "{}",
       success: function(result){
           UpdateTestIndex(result, option.dataIndexDivId, option.dataIndexDefaultStr, true, option.enableAvg);
       }
   });
}

