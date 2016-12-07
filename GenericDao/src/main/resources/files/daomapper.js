<script>
var table = 'Cliente';
var fieldsString = 'private String nome;private String email;private Boolean ativo;';
fieldsString = fieldsString.replace(/private /g,'');

var fields = fieldsString.split(';');
document.write('@Override\npublic void prepareInsert(' + table + ' entity) throws SQLException {');
for (i = 0; i < (fields.length - 1); i++) { 
	var variableAttribute = fields[i].split(' ');
	document.write('getPs().set' + variableAttribute[0] + '('  + (i + 1) + ',entity.get' + capitalize(variableAttribute[1] + '());'));
};
document.write('}');

document.write('@Override\npublic void prepareUpdate(' + table + ' entity) throws SQLException {');
for (i = 0; i < (fields.length - 1); i++) { 
	var variableAttribute = fields[i].split(' ');
	document.write('getPs().set' + variableAttribute[0] + '('  + (i + 1) + ',entity.get' + capitalize(variableAttribute[1] + '());'));
};

var fieldsStringPk = 'private Long id;private String nome;';
fieldsStringPk = fieldsStringPk.replace(/private /g,'');
var fieldsPk = fieldsStringPk.toString().split(';');
for (i = 0; i < (fieldsPk.length - 1); i++) { 
	var variableAttribute = fieldsPk[i].split(' ');
	document.write('getPs().set' + variableAttribute[0] + '('  + (i + fields.length) + ',entity.get' + capitalize(variableAttribute[1] + '());'));
};
document.write('}');

var fieldsString = 'private Long id;private String nome;private String email;private Boolean ativo;';
fieldsString = fieldsString.replace(/private /g,'');

var fields = fieldsString.split(';');
document.write('@Override\npublic ' + table + ' extract(ResultSet rs) throws SQLException {');
document.write(table + ' entity = new ' + table + '();');
    for (i = 0; i < (fields.length - 1); i++) { 
	var variableAttribute = fields[i].split(' ');
	document.write('entity.set' + capitalize(variableAttribute[1]) + '(rs.get' + (variableAttribute[0] == 'Integer' ? 'Int' : variableAttribute[0]) + '("' + table.toLowerCase() + '_' + (variableAttribute[1] + '"));'));
};
document.write('return entity;}');

function capitalize (text) {
    return text.charAt(0).toUpperCase() + text.slice(1).toLowerCase();
}
</script>
