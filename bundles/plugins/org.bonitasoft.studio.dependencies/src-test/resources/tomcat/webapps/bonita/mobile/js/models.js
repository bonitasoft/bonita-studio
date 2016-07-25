//### MODEL ###//

$.ajaxPrefilter( function( options ) {
    options.url = '/bonita/API/' + options.url;
});

filtersTitles = new Array();
filtersTitles['assigned'] 	= '_("My tasks")';
filtersTitles['unassigned'] = '_("Available Tasks")';
filtersTitles['available'] = '_("To do")';
filtersTitles['performed'] = '_("Done")';
filtersTitles['ignored'] 	= '_("Hidden")';

function getProcess(id, callback){
	$.getJSON('bpm/process/' + id, callback);
}

function getProcessWithDeployedUser(id, callback){
	var param = {d: 'deployedBy'};
	$.getJSON('bpm/process/' + id, param, callback);
}

function getProcesses(user, callback){
	$.getJSON('bpm/process', {
		p: 0,
		c: 10,
		o: 'displayName ASC',
		f: 'user_id=' + user.user_id
	}, function(data){
		callback(data);
	});
}

function getApps(args){
	var url = 'bpm/process';
	var params = {
		p: args.currentPage - 1,
		c: args.itemsPerPage,
		o: 'displayName ASC',
		f: 'user_id=' + args.user_id,
	}
	
	$.getJSON(url, params, function(data, status, request){
		var totalItems = request.getResponseHeader('Content-Range').split('/')[1];
		args.onSuccess(data, totalItems);
	});
}

function getCasesByProcess(args){
	var url = 'bpm/case';
	
	var params = "p="+ (args.currentPage - 1) + "&c=" + args.itemsPerPage + "&o=id ASC" + "&f=processDefinitionId%3D" + args.processDefinitionId + "&f=user_id%3D" + args.user.user_id;   
	
	$.getJSON(url, params, function(data, status, request){
		var totalItems = request.getResponseHeader('Content-Range').split('/')[1];
		args.onSuccess(data, totalItems);
	});	
}

function getArchivedCasesByProcess(args){
	var url = 'bpm/archivedCase';
	var params = "p="+ (args.currentPage - 1) + "&c=" + args.itemsPerPage + "&o=id ASC" + "&f=processDefinitionId%3D" + args.processDefinitionId + "&f=user_id%3D" + args.user.user_id;
	
	$.getJSON(url, params, function(data, status, request){
		var totalItems = request.getResponseHeader('Content-Range').split('/')[1];
		args.onSuccess(data, totalItems);
	});	
}

/**
 * @param taskId : (obligatoire) id de la tâche souhaitée
 * @param performed : si passé à "true", cherche dans les tâches performed
 * @param onSuccess : fonction de callback
 * @param onError : fonction de callback
 */
function getTask(taskId, performed, onSuccess, onError){
	var apiName = (performed == true) ? 'archivedHumanTask' : 'humanTask';	
	var url = 'bpm/' + apiName + '/' + taskId;
	var params = {d: 'processId'};
	
	$.getJSON(url, params, onSuccess).error(onError);
}

/**
 * @param processId : (obligatoire) id du process des tâches souhaitées
 * @param page : (obligatoire) la page souhaitée
 * @param itemsPerPage : (obligatoire) le nombre d'éléments par page
 * @param search : mots clefs filtrant les résultats
 * @param onSuccess : fonction de callback
 */
function getTasksByProcess(args){
	var url = 'bpm/humanTask';
	var params = {
		p: args.currentPage - 1,
		c: args.itemsPerPage,
		o: 'displayName ASC',
		d: 'processId',
		n: 'nb_of_comment'
	};
	
	if(args.search != null)
		params.s = args.search;
	
	if(args.processId == 'available') {
		url += '?f=state%3dready&f=user_id%3d' + args.user.user_id;
	} else if(args.processId == 'unassigned') {
		url += '?f=state%3dready&f=user_id%3d' + args.user.user_id + '&f=assigned_id%3d0&d=processId';
	} else if(args.processId == 'assigned') {
		url += '?f=state%3dready&f=assigned_id%3d' + args.user.user_id + '&d=processId';
	} else if(args.processId == 'ignored') {
		url += '?f=hidden_user_id%3d' + args.user.user_id + '&d=processId';
	} else if(args.processId == 'performed') {
		url = 'bpm/archivedHumanTask' + '?f=assigned_id%3d' + args.user.user_id;
	} else {
		url += '?f=state%3dready&f=user_id%3d' + args.user.user_id + '&f=processId%3d' + args.processId;
	}
	
	$.getJSON(url, params, function(data, status, request){
		var totalItems = request.getResponseHeader('Content-Range').split('/')[1];
		args.onSuccess(data, totalItems, args.processId);
	});
}

function countTasksByProcess(processId, user, onSuccess){
	getTasksByProcess({
		processId: processId,
		user: user,
		currentPage: 1,
		itemsPerPage: 0,
		onSuccess: onSuccess
	});
}

function getCommentsByCase(caseId, currentPage, itemsPerPage, callback){
	var params = {
		p: 0,
		c: 1000,
		o: 'postdate DESC',
	};
	
	var url = 'bpm/comment' + '?f=IS_SOCIALBAR%3dfalse&f=CASE_ID%3d' + caseId;
	
	$.getJSON(url, params, function(data){
		callback({'comments' : data});
	});
}

function getCommentsByCase(caseId, currentPage, itemsPerPage, callback){
	var params = {
		p: currentPage - 1,
		c: itemsPerPage,
		o: 'postDate DESC',
		d: 'userId'
	};
	
	var url = 'bpm/comment' + '?f=processInstanceId%3d' + caseId;
	
	$.getJSON(url, params, function(data, status, request){
		var totalItems = request.getResponseHeader('Content-Range').split('/')[1];
		callback(data, totalItems);
	});
}

function createComment(caseId, comment, callback){
	if(comment == "") return false;
	var params = {
		'processInstanceId': caseId,
		'content': comment
	};
	
	console.log(params);
	
	$.ajax({  
		  url: 'bpm/comment/',  
		  type: "POST",  
		  dataType: "json",
		  contentType: "application/json; charset=utf-8",
		  data: JSON.stringify(params)
	}).done(callback);
}

function getSubtasksByTask(id, state, page, itemsPerPage, callback){
	page--;
	
	var params = {
		p: page,
		c: itemsPerPage
	};
	
	var url = 'bpm/';
	url += (state == 'available') ? 'manualTask' : 'archivedManualTask';
	url += '?f=parentTaskId%3d' + id;
	
	$.getJSON(url, params, function(data, status, request){
		var totalItems = request.getResponseHeader('Content-Range').split('/')[1];
		callback(data, totalItems);
	});
}

function createSubtask(subtask, callback){
	$.ajax({
		url: 'bpm/manualTask/',
		type: "POST",
		dataType: "json",
		contentType: "application/json; charset=utf-8",
		data: JSON.stringify(subtask)
	}).done(callback);
}

function assignTask(id, callback){
	$.ajax({
		url: 'bpm/humanTask/' + id,
		type: 'PUT',
		data: JSON.stringify({ assigned_id: user.user_id })
	}).done(callback());
}

function unassignTask(id, callback){
	var params = {
		assigned_id: ''
	};
	
	$.ajax({
		url: 'bpm/humanTask/' + id,
		type: 'PUT',
		data: JSON.stringify(params)
	}).done(callback());
}

function performTask(taskId, callback){
	$.ajax({
		url: 'bpm/humanTask/' + taskId,
		data: JSON.stringify({ state: 'completed' }),
		type: 'PUT',
	}).done(callback());
}

function ignoreTask(taskId, userId, callback){
	var params = {
		'user_id': userId,
		'task_id': taskId
	};
	
	$.ajax({
		url: 'bpm/hiddenUserTask/',
		type: "POST",
		dataType: "json",
		contentType: "application/json; charset=utf-8",
		data: JSON.stringify(params)
	}).done(callback);
}

function unignoreTask(taskId, userId, callback){
	$.ajax({
		url: 'bpm/hiddenUserTask/' + userId + '/' + taskId,
		type: 'DELETE',
	}).done(callback());
}

function getLanguages(callback){
	$.getJSON('system/i18nlocale/?p=0&c=100', function(data){
		callback(data);
	});
}
