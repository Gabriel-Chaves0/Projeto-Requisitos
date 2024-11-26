import React, { useState } from "react";
import Button from "./Button";
import { motion } from "framer-motion";
import { useForm } from "react-hook-form";
import { useNavigate } from "react-router-dom";
import api from "../services/api";

const RegisterForm = () => {
	const navigate = useNavigate();
	const [visiblePassword, setVisiblePassword] = useState({
		password: false,
		passwordRetry: false,
	});

	const handlePasswordType = (passwordInput) => {
		setVisiblePassword({
			...visiblePassword,
			[passwordInput]: !visiblePassword[passwordInput],
		});
	};

	const {
		register,
		handleSubmit,
		watch,
		formState: { errors },
	} = useForm({
		mode: "onBlur",
		reValidateMode: "onChange",
	});

	const onSubmit = async (data) => {
		// Formatar a data de nascimento para o formato Ano/Mês/Dia
		const dataNascimento = new Date(data.dataNascimento);
		const formattedDate = `${dataNascimento.getFullYear()}-${String(
			dataNascimento.getMonth() + 1
		).padStart(2, "0")}-${String(dataNascimento.getDate()).padStart(2, "0")}`;

		// Gerar um ID aleatório
		const randomId = Math.floor(Math.random() * 1000000);

		// Criar o objeto com os campos na ordem específica, incluindo o ID
		const formattedData = {
			id: randomId,
			rua: data.rua,
			cidade: data.cidade,
			telefone: data.telefone,
			email: data.email,
			nome: data.nome,
			cpf: data.cpf,
			dataNascimento: formattedDate,
		};

		try {
			// Usar Axios para enviar uma requisição POST para o endpoint /pessoas
			const response = await api.post("/pessoas", formattedData);

			if (response.status === 200) {
				// Salva o CPF no localStorage para uso posterior
				localStorage.setItem("cpf", data.cpf);

				alert("Cadastro realizado com sucesso!");
				navigate("/login");
			} else {
				const errorMessage = response.data.message || "Erro ao cadastrar.";
				alert(errorMessage);
			}
		} catch (error) {
			console.error("Erro na requisição:", error);
			if (error.response && error.response.data && error.response.data.message) {
				alert(`Erro ao cadastrar: ${error.response.data.message}`);
			} else {
				alert("Erro ao cadastrar. Tente novamente mais tarde.");
			}
		}
	};

	return (
		<motion.section
			className="register"
			initial={{ width: 0 }}
			animate={{ width: "auto", transition: { duration: 0.5 } }}
			exit={{ x: window.innerWidth, transition: { duration: 0.5 } }}
		>
			<img src="logo-blue.svg" alt="" />
			<p>
				Ainda não tem cadastro? <br /> Então, antes de buscar seu melhor amigo,
				precisamos de alguns dados:
			</p>
			<form onSubmit={handleSubmit(onSubmit)}>
				<label htmlFor="rua">Rua</label>
				<input
					id="rua"
					type="text"
					{...register("rua", {
						required: "É necessário informar sua rua",
						maxLength: {
							value: 50,
							message: "O número máximo de caracteres é 50",
						},
					})}
					placeholder="Digite sua rua"
				/>
				{errors.rua && <p className="error">{errors.rua.message}</p>}

				<label htmlFor="cidade">Cidade</label>
				<input
					id="cidade"
					type="text"
					{...register("cidade", {
						required: "É necessário informar sua cidade",
						maxLength: {
							value: 30,
							message: "O número máximo de caracteres é 30",
						},
					})}
					placeholder="Digite sua cidade"
				/>
				{errors.cidade && <p className="error">{errors.cidade.message}</p>}

				<label htmlFor="telefone">Telefone</label>
				<input
					id="telefone"
					type="text"
					{...register("telefone", {
						required: "É necessário informar seu telefone",
						pattern: {
							value: /^\(?\d{2}\)?[\s-]?[\s9]?\d{4}-?\d{4}$/,
							message: "Por favor, verifique o telefone digitado",
						},
					})}
					placeholder="Digite seu telefone"
				/>
				{errors.telefone && <p className="error">{errors.telefone.message}</p>}

				<label htmlFor="email">E-mail</label>
				<input
					id="email"
					type="email"
					{...register("email", {
						required: "É necessário informar um endereço de email",
						pattern:
							/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/,
					})}
					placeholder="Escolha seu melhor email"
				/>
				{errors.email && (
					<p className="error">
						{errors.email.message || "Por favor, verifique o email digitado"}
					</p>
				)}

				<label htmlFor="name">Nome</label>
				<input
					id="name"
					type="text"
					{...register("nome", {
						required: "É necessário informar seu nome",
						maxLength: {
							value: 25,
							message: "O número máximo de caracteres é 25",
						},
					})}
					placeholder="Digite seu nome completo"
				/>
				{errors.nome && <p className="error">{errors.nome.message}</p>}

				<label htmlFor="cpf">CPF</label>
				<input
					id="cpf"
					type="text"
					{...register("cpf", {
						required: "É necessário informar seu CPF",
						pattern: {
							value: /^\d{11}$/,
							message: "O CPF deve conter 11 dígitos numéricos",
						},
					})}
					placeholder="Digite seu CPF"
				/>
				{errors.cpf && <p className="error">{errors.cpf.message}</p>}

				<label htmlFor="dataNascimento">Data de Nascimento</label>
				<input
					id="dataNascimento"
					type="date"
					{...register("dataNascimento", {
						required: "É necessário informar sua data de nascimento",
					})}
				/>
				{errors.dataNascimento && (
					<p className="error">{errors.dataNascimento.message}</p>
				)}

				{/* Campos de senha omitidos, pois não são enviados para a API conforme especificado */}

				<Button type="submit">Cadastrar</Button>
			</form>
		</motion.section>
	);
};

export default RegisterForm;
