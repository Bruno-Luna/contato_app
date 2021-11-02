	function salvar() {
			var id = $("#id").val();
			var nome = $("#nome").val();
			var contato = $("#contato").val();

			if (nome == null || nome != null && nome.trim() == '') {
				$("#nome").focus();
				alert('O campo nome está vazio.');
				return;
			}

			$.ajax({
				method : "POST",
				url : "salvar",
				data : JSON.stringify({
					id : id,
					nome : nome,
					contato : contato
				}),
				contentType : 'application/json; charset=utf-8',
				success : function(response) {
					$("#id").val(response.id);
					alert("Salvado com sucesso!")
				}
			}).fail(function(xhr, status, errorThrown) {
				alert("Erro ao salvar: " + xhr.responseText);
			});
		}

		function limpar() {
			document.getElementById('form').reset();
		}

		function buscar() {
			nome = $('#nomeBuscar').val();

			if (nome != null && nome.trim() != '') {
				$
						.ajax(
								{
									method : "GET",
									url : "buscarNome",
									data : "nome=" + nome,
									success : function(response) {
										$('#tabelaResultado > tbody > tr')
												.remove();

										for (var i = 0; i < response.length; i++) {
											$('#tabelaResultado > tbody')
													.append(
															'<tr id="'+response[i].id+'"><td>'
																	+ response[i].id
																	+ '</td><td>'
																	+ response[i].nome
																	+ '</td><td>'
																	+ response[i].contato
																	+ '</td><td>	<span class="btnIcon editar" onclick="edicao('
																	+ response[i].id
																	+ ')"><i class="far fa-edit"></i></span></td><td><span class="btnIcon apagar" onclick="apagar('
																	+ response[i].id
																	+ ')"><i class="far fa-trash-alt"></i></span></td></tr>');
										}
									}
								}).fail(function(xhr, status, errorThrown) {
							alert("Erro ao buscar: " + xhr.responseText);
						});

			}
		}

		function listarTodos() {
			$
					.ajax(
							{
								method : "GET",
								url : "listarTodos",
								success : function(response) {
									$('#tabelaResultado > tbody > tr').remove();

									for (var i = 0; i < response.length; i++) {
										$('#tabelaResultado > tbody')
												.append(
														'<tr id="'+response[i].id+'"><td>'
																+ response[i].id
																+ '</td><td>'
																+ response[i].nome
																+ '</td><td>'
																+ response[i].contato
																+ '</td><td>	<span class="btnIcon editar" onclick="edicao('
																+ response[i].id
																+ ')"><i class="far fa-edit"></i></span></td><td><span class="btnIcon apagar" onclick="apagar('
																+ response[i].id
																+ ')"><i class="far fa-trash-alt"></i></span></td></tr>');
									}
								}
							}).fail(function(xhr, status, errorThrown) {
						alert("Erro ao buscar: " + xhr.responseText);
					});
		}

		function edicao(id) {

			$.ajax({
				method : "GET",
				url : "buscarId",
				data : "id=" + id,
				success : function(response) {

					$("#id").val(response.id);
					$("#nome").val(response.nome);
					$("#contato").val(response.contato);

					$('#modalPesquisar').modal('hide')

				}
			}).fail(function(xhr, status, errorThrown) {
				alert("Erro ao buscar contato por ID: " + xhr.responseText);
			});
		}

		function apagar(id) {

			if (confirm('Deseja realmente apagar este contato?')) {

				$.ajax({
					method : "DELETE",
					url : "deletar",
					data : "id=" + id,
					success : function(response) {

						$('#' + id).remove();

						alert(response);
					}
				}).fail(
						function(xhr, status, errorThrown) {
							alert("Erro ao deletar contato por ID: "
									+ xhr.responseText);
						});
			}
		}