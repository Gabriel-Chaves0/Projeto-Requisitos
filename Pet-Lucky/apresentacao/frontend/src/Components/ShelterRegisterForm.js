import React from "react";
import Button from "./Button";
import { motion } from "framer-motion";
import { useForm } from "react-hook-form";
import { useNavigate } from "react-router-dom";

const AbrigoRegisterForm = () => {
    const navigate = useNavigate();

    const {
        register,
        handleSubmit,
        reset,
        formState: { errors },
    } = useForm({
        mode: "onBlur",
        reValidateMode: "onChange",
    });

    const onSubmit = async (data) => {
        // Log dos dados do formulário para depuração
        console.log("Dados do formulário:", data);

        // Gerar um ID aleatório para o abrigo
        const randomId = Math.floor(Math.random() * 1000000);
        console.log("ID Aleatório do Abrigo:", randomId);

        // Criar o objeto com os campos necessários
        const formattedData = {
            idAbrigo: randomId,
            nomeAbrigo: data.nomeAbrigo,
            rua: data.rua,
            cidade: data.cidade,
            telefone: data.telefone,
            email: data.email,
            capacidadeAbrigo: parseInt(data.capacidadeAbrigo),
        };

        // Log dos dados formatados para depuração
        console.log("Dados formatados:", formattedData);

        try {
            // Enviar uma requisição POST para o endpoint /api/adocao/abrigo
            const response = await fetch("http://localhost:8080/api/adocao/abrigo", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(formattedData),
            });

            if (response.ok) {
                alert("Abrigo cadastrado com sucesso!");
                reset(); // Redefine o formulário após o envio
                navigate("/"); // Navega para a página de lista de abrigos ou outra conforme necessário
            } else {
                const errorData = await response.json();
                const errorMessage = errorData.message || "Erro ao cadastrar o abrigo.";
                alert(errorMessage);
            }
        } catch (error) {
            console.error("Erro na requisição:", error);
            alert("Erro ao cadastrar o abrigo. Tente novamente mais tarde.");
        }
    };

    return (
        <motion.section
            className="register"
            initial={{ width: 0 }}
            animate={{ width: "auto", transition: { duration: 0.5 } }}
            exit={{ x: window.innerWidth, transition: { duration: 0.5 } }}
        >
            <h1>Cadastro de Abrigo</h1>
            <p>Preencha as informações para cadastrar um novo abrigo:</p>
            <form onSubmit={handleSubmit(onSubmit)}>
                <label htmlFor="nomeAbrigo">Nome do Abrigo</label>
                <input
                    id="nomeAbrigo"
                    type="text"
                    {...register("nomeAbrigo", {
                        required: "É necessário informar o nome do abrigo",
                        maxLength: {
                            value: 100,
                            message: "O número máximo de caracteres é 100",
                        },
                    })}
                    placeholder="Digite o nome do abrigo"
                />
                {errors.nomeAbrigo && <p className="error">{errors.nomeAbrigo.message}</p>}

                <label htmlFor="rua">Rua</label>
                <input
                    id="rua"
                    type="text"
                    {...register("rua", {
                        required: "É necessário informar a rua",
                        maxLength: {
                            value: 100,
                            message: "O número máximo de caracteres é 100",
                        },
                    })}
                    placeholder="Digite a rua do abrigo"
                />
                {errors.rua && <p className="error">{errors.rua.message}</p>}

                <label htmlFor="cidade">Cidade</label>
                <input
                    id="cidade"
                    type="text"
                    {...register("cidade", {
                        required: "É necessário informar a cidade",
                        maxLength: {
                            value: 50,
                            message: "O número máximo de caracteres é 50",
                        },
                    })}
                    placeholder="Digite a cidade do abrigo"
                />
                {errors.cidade && <p className="error">{errors.cidade.message}</p>}

                <label htmlFor="telefone">Telefone</label>
                <input
                    id="telefone"
                    type="text"
                    {...register("telefone", {
                        required: "É necessário informar o telefone",
                        pattern: {
                            value: /^\(?\d{2}\)?[\s-]?[\s9]?\d{4}-?\d{4}$/,
                            message: "Por favor, verifique o telefone digitado",
                        },
                    })}
                    placeholder="Digite o telefone do abrigo"
                />
                {errors.telefone && <p className="error">{errors.telefone.message}</p>}

                <label htmlFor="email">E-mail</label>
                <input
                    id="email"
                    type="email"
                    {...register("email", {
                        required: "É necessário informar um endereço de email",
                        pattern: {
                            value:
                                /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/,
                            message: "Por favor, verifique o email digitado",
                        },
                    })}
                    placeholder="Digite o email do abrigo"
                />
                {errors.email && <p className="error">{errors.email.message}</p>}

                <label htmlFor="capacidadeAbrigo">Capacidade do Abrigo</label>
                <input
                    id="capacidadeAbrigo"
                    type="number"
                    {...register("capacidadeAbrigo", {
                        required: "É necessário informar a capacidade do abrigo",
                        min: {
                            value: 1,
                            message: "A capacidade deve ser no mínimo 1",
                        },
                    })}
                    placeholder="Digite a capacidade do abrigo"
                />
                {errors.capacidadeAbrigo && <p className="error">{errors.capacidadeAbrigo.message}</p>}

                <Button type="submit">Cadastrar Abrigo</Button>
            </form>
        </motion.section>
    );
};

export default AbrigoRegisterForm;
