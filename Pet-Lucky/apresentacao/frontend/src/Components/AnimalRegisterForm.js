import React, { useState, useEffect } from "react";
import Button from "./Button";
import { motion } from "framer-motion";
import { useForm } from "react-hook-form";
import { useNavigate } from "react-router-dom";

const AnimalRegisterForm = () => {
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

    const [abrigos, setAbrigos] = useState([]);
    const [loadingAbrigos, setLoadingAbrigos] = useState(true);
    const [errorAbrigos, setErrorAbrigos] = useState(null);

    useEffect(() => {
        const fetchAbrigos = async () => {
            try {
                const response = await fetch("http://localhost:8080/api/adocao/abrigos");
                if (response.ok) {
                    const data = await response.json();
                    console.log("Abrigos recebidos:", data);
                    setAbrigos(data);
                    setLoadingAbrigos(false);
                } else {
                    throw new Error("Erro ao obter a lista de abrigos.");
                }
            } catch (error) {
                console.error("Erro ao buscar abrigos:", error);
                setErrorAbrigos(error.message);
                setLoadingAbrigos(false);
            }
        };

        fetchAbrigos();
    }, []);

    const [pessoas, setPessoas] = useState([]);

    useEffect(() => {
        const fetchUsuarios = async () => {
            try{
                const response = await fetch("http://localhost:8080/api/pessoas");
                if (response.ok) {
                    const data = await response.json();
                    console.log("Pessoas recebidos:", data);
                    setPessoas(data)
                }
                else {
                    throw new Error("Erro ao obter a lista de pessoas.");
                }
            } catch ( error){
                console.error("Erro ao obter a lista de pessoas.");
            }
        }
        fetchUsuarios()
    }, []);

    const getAbrigoId = (abrigo) => {
        if (abrigo.idAbrigo && abrigo.idAbrigo.id) {
            return abrigo.idAbrigo.id;
        }
        console.error('Não foi possível obter o ID do abrigo:', abrigo);
        return null;
    };

    const getPessoaId = (pessoa) => {
        if (pessoa.idPessoa && pessoa.idPessoa.id) {
            return pessoa.idPessoa.id;
        }
        console.error('Não foi possível obter o ID de pessoa:', pessoa);
        return null;
    };

    const onSubmit = async (data) => {
        console.log("Dados do formulário:", data);

        const randomId = Math.floor(Math.random() * 1000000);

        const formattedData = {
            id: randomId,
            idAbrigo: parseInt(data.idAbrigo, 10),
            idAdotante: parseInt(data.idAdotante, 10),
            vacinaRaiva: false,
            vermifugado: false,
            castrado: false,
            nomeAnimal: data.nomeAnimal,
            idadeAnimal: data.idadeAnimal,
            especie: data.especie,
            raca: data.raca || "SRD",
            porte: data.porte,
            sexo: data.sexo,
            adotado: false,
        };

        console.log("Dados formatados:", formattedData);

        try {
            const response = await fetch("http://localhost:8080/api/animais", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(formattedData),
            });

            if (response.ok) {
                alert("Animal cadastrado com sucesso!");
                reset();
                navigate("/home");
            } else {
                const errorData = await response.json();
                const errorMessage = errorData.message || "Erro ao cadastrar o animal.";
                alert(errorMessage);
            }
        } catch (error) {
            console.error("Erro na requisição:", error);
            alert("Erro ao cadastrar o animal. Tente novamente mais tarde.");
        }
    };

    if (loadingAbrigos) {
        return <p>Carregando abrigos...</p>;
    }

    if (errorAbrigos) {
        return <p className="error">{errorAbrigos}</p>;
    }

    return (
        <motion.section
            className="register"
            initial={{ width: 0 }}
            animate={{ width: "auto", transition: { duration: 0.5 } }}
            exit={{ x: window.innerWidth, transition: { duration: 0.5 } }}
        >
            <h1>Cadastro de Animal</h1>
            <p>Preencha as informações do animal para disponibilizá-lo para adoção:</p>
            <form onSubmit={handleSubmit(onSubmit)}>
                <label htmlFor="idAbrigo">Abrigo</label>
                <select
                    id="idAbrigo"
                    {...register("idAbrigo", {
                        required: "É necessário selecionar um abrigo",
                    })}
                >
                    <option value="">Selecione um abrigo</option>
                    {abrigos.map((abrigo) => {
                        const id = getAbrigoId(abrigo);
                        return (
                            <option key={id} value={id}>
                                {abrigo.nomeAbrigo}
                            </option>
                        );
                    })}
                </select>
                {errors.idAbrigo && <p className="error">{errors.idAbrigo.message}</p>}
                <label htmlFor="idPessoa">Pessoa</label>
                <select
                    id="idAPessoa"
                    {...register("idAdotante", {
                        required: "É necessário selecionar uma Pessoa",
                    })}
                >
                    <option value="">Selecione uma Pessoa</option>
                    {pessoas.map((pessoa) => {
                        const id = getPessoaId(pessoa);
                        return (
                            <option key={id} value={id}>
                                {pessoa.nomePessoa}
                            </option>
                        );
                    })}
                </select>

                <label htmlFor="nomeAnimal">Nome do Animal</label>
                <input
                    id="nomeAnimal"
                    type="text"
                    {...register("nomeAnimal", {
                        required: "É necessário informar o nome do animal",
                        maxLength: {
                            value: 50,
                            message: "O número máximo de caracteres é 50",
                        },
                    })}
                    placeholder="Digite o nome do animal"
                />
                {errors.nomeAnimal && <p className="error">{errors.nomeAnimal.message}</p>}

                <label htmlFor="idadeAnimal">Idade do Animal</label>
                <input
                    id="idadeAnimal"
                    type="text"
                    {...register("idadeAnimal", {
                        required: "É necessário informar a idade do animal",
                        maxLength: {
                            value: 20,
                            message: "O número máximo de caracteres é 20",
                        },
                    })}
                    placeholder="Digite a idade do animal (e.g., 2 anos)"
                />
                {errors.idadeAnimal && <p className="error">{errors.idadeAnimal.message}</p>}

                <label htmlFor="especie">Espécie</label>
                <select
                    id="especie"
                    {...register("especie", {
                        required: "É necessário selecionar a espécie do animal",
                    })}
                >
                    <option value="">Selecione a espécie</option>
                    <option value="Cachorro">Cachorro</option>
                    <option value="Gato">Gato</option>
                </select>
                {errors.especie && <p className="error">{errors.especie.message}</p>}

                <label htmlFor="raca">Raça (Opcional)</label>
                <input
                    id="raca"
                    type="text"
                    {...register("raca", {
                        maxLength: {
                            value: 30,
                            message: "O número máximo de caracteres é 30",
                        },
                    })}
                    placeholder="Digite a raça do animal ou deixe em branco"
                />
                {errors.raca && <p className="error">{errors.raca.message}</p>}

                <label htmlFor="porte">Porte</label>
                <select
                    id="porte"
                    {...register("porte", {
                        required: "É necessário selecionar o porte do animal",
                    })}
                >
                    <option value="">Selecione o porte</option>
                    <option value="Pequeno">Pequeno</option>
                    <option value="Médio">Médio</option>
                    <option value="Grande">Grande</option>
                </select>
                {errors.porte && <p className="error">{errors.porte.message}</p>}

                <label htmlFor="sexo">Sexo</label>
                <select
                    id="sexo"
                    {...register("sexo", {
                        required: "É necessário selecionar o sexo do animal",
                    })}
                >
                    <option value="">Selecione o sexo</option>
                    <option value="Macho">Macho</option>
                    <option value="Fêmea">Fêmea</option>
                </select>
                {errors.sexo && <p className="error">{errors.sexo.message}</p>}

                <Button type="submit">Cadastrar Animal</Button>
            </form>
        </motion.section>
    );
};

export default AnimalRegisterForm;
