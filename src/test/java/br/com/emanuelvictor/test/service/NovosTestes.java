package br.com.emanuelvictor.test.service;

import br.com.emanuelvictor.iguassu.web.entity.*;
import br.com.emanuelvictor.iguassu.web.entity.address.Bairro;
import br.com.emanuelvictor.iguassu.web.entity.address.Cidade;
import br.com.emanuelvictor.iguassu.web.entity.address.Estado;
import br.com.emanuelvictor.iguassu.web.entity.address.Pais;
import br.com.emanuelvictor.iguassu.web.entity.job.Cargo;
import br.com.emanuelvictor.iguassu.web.entity.job.Experiencia;
import br.com.emanuelvictor.iguassu.web.entity.job.vacancy.Vaga;
import br.com.emanuelvictor.iguassu.web.entity.schooling.CandidatoCurso;
import br.com.emanuelvictor.iguassu.web.entity.schooling.CategoriaCurso;
import br.com.emanuelvictor.iguassu.web.entity.schooling.Curso;
import br.com.emanuelvictor.iguassu.web.repository.DAOBaseEntity;
import br.com.emanuelvictor.iguassu.web.service.ServiceCandidato;
import br.com.emanuelvictor.iguassu.web.service.ServiceEmpresa;
import br.com.emanuelvictor.iguassu.web.service.ServiceEncaminhamento;
import br.com.emanuelvictor.iguassu.web.service.address.ServiceEndereco;
import br.com.emanuelvictor.iguassu.web.service.job.ServiceCargo;
import br.com.emanuelvictor.iguassu.web.service.job.vacancy.ServiceVaga;
import br.com.emanuelvictor.iguassu.web.service.schooling.ServiceCategoriaCurso;
import br.com.emanuelvictor.iguassu.web.service.schooling.ServiceCurso;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;

import static org.junit.Assert.assertTrue;

@TransactionConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class NovosTestes {


    @Autowired
    ServiceEncaminhamento serviceEncaminhamento;

    @Autowired
    ServiceVaga serviceVaga;

    @Autowired
    ServiceCandidato serviceCandidato;

    @Autowired
    ServiceCurso serviceCurso;

    @Autowired
    ServiceCategoriaCurso serviceCategoriaCurso;

    @Autowired
    DAOBaseEntity daoBaseEntity;
    @Autowired
    ServiceCargo serviceCargo;

    @Autowired
    ServiceEndereco serviceEndereco;

    @Autowired
    ServiceEmpresa serviceEmpresa;


    @Test
    @Rollback(false)
    @Transactional
    public void saveBaseEntityCEP() {
//        daoBaseEntity.find("85862590");
    }

    @Test
    @Rollback(false)
    @Transactional
    public void saveCandidato() {

        Pais pais = new Pais();
        pais.setNome("Brasil");
        pais = serviceEndereco.save(pais);

        Estado estado = new Estado();
        estado.setNome("Paraná");
        estado.setPais(pais);
        estado = serviceEndereco.save(estado);

        Cidade cidade = new Cidade();
        cidade.setNome("Foz do Iguaçu");
        cidade.setEstado(estado);
        cidade = serviceEndereco.save(cidade);

        Bairro bairro = new Bairro();
        bairro.setNome("Três Lagoas");
        bairro.setCidade(cidade);
        bairro = serviceEndereco.save(bairro);

        Empresa udc = new Empresa();
        udc.setCnpj("33123123");
        udc.setBairro(bairro);
        udc.setNome("UDC");
        udc = serviceEmpresa.save(udc);

        Empresa escolaMunicipalJoaoDaCostaViana = new Empresa();
        escolaMunicipalJoaoDaCostaViana.setCnpj("3312123123");
        escolaMunicipalJoaoDaCostaViana.setBairro(bairro);
        escolaMunicipalJoaoDaCostaViana
                .setNome("Escola Municipal João da Costa Viana");
        escolaMunicipalJoaoDaCostaViana = serviceEmpresa
                .save(escolaMunicipalJoaoDaCostaViana);

        Empresa escolaEstadualArnaldoBusatto = new Empresa();
        escolaEstadualArnaldoBusatto.setCnpj("3312543523");
        escolaEstadualArnaldoBusatto.setBairro(bairro);
        escolaEstadualArnaldoBusatto
                .setNome("Escola Estado Dr. Arnaldo busato");
        escolaEstadualArnaldoBusatto = serviceEmpresa
                .save(escolaEstadualArnaldoBusatto);

        Empresa technosInformatica = new Empresa();
        technosInformatica.setCnpj("312323123");
        technosInformatica.setBairro(bairro);
        technosInformatica.setNome("Technos Informática");
        technosInformatica = serviceEmpresa.save(technosInformatica);

        Candidato sarah = new Candidato();
        sarah.setNome("Sarah de Oliveira Fonseca ");
        sarah.setGenero(Genero.FEMININO);
        sarah.setDataNasc(Calendar.getInstance());
        sarah.setSituacao(SituacaoDoCandidato.BLOQUEADO);
        sarah.setBairro(bairro);
        sarah.setNacionalidade(pais);
        sarah = serviceCandidato.save(sarah);

        Candidato emanuel = new Candidato();
        emanuel.setNome("Emanuel Victor de Oliveira Fonseca ");
        emanuel.setGenero(Genero.MASCULINO);
        emanuel.setDataNasc(Calendar.getInstance());
        emanuel.setSituacao(SituacaoDoCandidato.BLOQUEADO);
        emanuel.setBairro(bairro);
        emanuel.setNacionalidade(pais);
        emanuel = serviceCandidato.save(emanuel);

        Candidato mayara = new Candidato();
        mayara.setNome("Mayara Regina Borges ");
        mayara.setGenero(Genero.FEMININO);
        mayara.setDataNasc(Calendar.getInstance());
        mayara.setSituacao(SituacaoDoCandidato.BLOQUEADO);
        mayara.setBairro(bairro);
        mayara.setNacionalidade(pais);
        mayara = serviceCandidato.save(mayara);

        Candidato anderson = new Candidato();
        anderson.setNome("Anderson Coutinho ");
        anderson.setGenero(Genero.MASCULINO);
        anderson.setDataNasc(Calendar.getInstance());
        anderson.setSituacao(SituacaoDoCandidato.BLOQUEADO);
        anderson.setBairro(bairro);
        anderson.setNacionalidade(pais);
        anderson = serviceCandidato.save(anderson);

        // cARGOS

        Cargo analistaDeSistemas = new Cargo();
        analistaDeSistemas.setNome("Analista de Sistemas");
        analistaDeSistemas.setDescricao("Ficar rico");
        analistaDeSistemas = serviceCargo.save(analistaDeSistemas);

        Cargo tecnicoDeInformatica = new Cargo();
        tecnicoDeInformatica.setNome("Técnico em Informática");
        tecnicoDeInformatica.setDescricao("Sofrer");
        tecnicoDeInformatica = serviceCargo.save(tecnicoDeInformatica);

        Cargo atendenteComercial = new Cargo();
        atendenteComercial.setNome("Atendente Comercial");
        atendenteComercial.setDescricao("Atender comercialmente clientes");
        atendenteComercial = serviceCargo.save(atendenteComercial);

        Cargo quality = new Cargo();
        quality.setNome("Ouvir telefonema das pessoas");
        quality.setDescricao("Ouvir o telefonema das pessoas e converter par amenszagens");
        quality = serviceCargo.save(quality);

        Cargo gerenteComercial = new Cargo();
        gerenteComercial.setNome("Gerente Comercial");
        gerenteComercial
                .setDescricao("Ser chefe de quem é atendente comercial");
        gerenteComercial = serviceCargo.save(gerenteComercial);

        Cargo pedagogo = new Cargo();
        pedagogo.setNome("Pedagogo (a)");
        pedagogo.setDescricao("Cuidar do futuro do nação");
        pedagogo = serviceCargo.save(pedagogo);

        // Experiencia Sarah

        Experiencia experienciaEmPedagoia = new Experiencia();
        experienciaEmPedagoia.setCandidato(sarah);
        experienciaEmPedagoia.setCargo(pedagogo);
        experienciaEmPedagoia.setEmpresa(udc);
        experienciaEmPedagoia.setDataInicio(Calendar.getInstance());
        experienciaEmPedagoia = serviceCandidato
                .save(experienciaEmPedagoia);

        Experiencia experienciaEmAtendenteComercialSarah = new Experiencia();
        experienciaEmAtendenteComercialSarah.setCandidato(sarah);
        experienciaEmAtendenteComercialSarah
                .setEmpresa(escolaMunicipalJoaoDaCostaViana);
        experienciaEmAtendenteComercialSarah.setCargo(atendenteComercial);
        experienciaEmAtendenteComercialSarah.setDataInicio(Calendar
                .getInstance());
        experienciaEmAtendenteComercialSarah = serviceCandidato
                .save(experienciaEmAtendenteComercialSarah);

        // Experiencia Emanuel
        Experiencia experienciaEmAnaliseDeSistemas = new Experiencia();
        experienciaEmAnaliseDeSistemas.setCandidato(emanuel);
        experienciaEmAnaliseDeSistemas.setCargo(analistaDeSistemas);
        experienciaEmAnaliseDeSistemas
                .setEmpresa(escolaMunicipalJoaoDaCostaViana);
        experienciaEmAnaliseDeSistemas
                .setDataInicio(Calendar.getInstance());
        experienciaEmAnaliseDeSistemas = serviceCandidato
                .save(experienciaEmAnaliseDeSistemas);

        Experiencia experienciaEmTecnicoEmInformatica = new Experiencia();
        experienciaEmTecnicoEmInformatica.setCandidato(emanuel);
        experienciaEmTecnicoEmInformatica.setCargo(tecnicoDeInformatica);
        experienciaEmTecnicoEmInformatica
                .setEmpresa(escolaMunicipalJoaoDaCostaViana);
        experienciaEmTecnicoEmInformatica.setDataInicio(Calendar
                .getInstance());
        experienciaEmTecnicoEmInformatica = serviceCandidato
                .save(experienciaEmTecnicoEmInformatica);

        Experiencia experienciaEmAtendenteComercialEmanuel = new Experiencia();
        experienciaEmAtendenteComercialEmanuel.setCandidato(emanuel);
        experienciaEmAtendenteComercialEmanuel
                .setEmpresa(technosInformatica);
        experienciaEmAtendenteComercialEmanuel.setCargo(atendenteComercial);
        experienciaEmAtendenteComercialEmanuel.setDataInicio(Calendar
                .getInstance());
        experienciaEmAtendenteComercialEmanuel = serviceCandidato
                .save(experienciaEmAtendenteComercialEmanuel);

        // Experiencia Mayara

        Experiencia experienciaEmAtendenteComercialMayara = new Experiencia();
        experienciaEmAtendenteComercialMayara.setCandidato(mayara);
        experienciaEmAtendenteComercialMayara
                .setEmpresa(escolaEstadualArnaldoBusatto);
        experienciaEmAtendenteComercialMayara.setCargo(atendenteComercial);
        experienciaEmAtendenteComercialMayara.setDataInicio(Calendar
                .getInstance());
        experienciaEmAtendenteComercialMayara = serviceCandidato
                .save(experienciaEmAtendenteComercialMayara);

        Experiencia experienciaEmGerenteComercialMayara = new Experiencia();
        experienciaEmGerenteComercialMayara.setCandidato(mayara);
        experienciaEmGerenteComercialMayara.setEmpresa(technosInformatica);
        experienciaEmGerenteComercialMayara.setCargo(gerenteComercial);
        experienciaEmGerenteComercialMayara.setDataInicio(Calendar
                .getInstance());
        experienciaEmGerenteComercialMayara = serviceCandidato
                .save(experienciaEmGerenteComercialMayara);

        // Experiencia Anderson

        Experiencia experienciaEmQuality = new Experiencia();
        experienciaEmQuality.setCandidato(anderson);
        experienciaEmQuality.setCargo(quality);
        experienciaEmQuality.setEmpresa(udc);
        experienciaEmQuality.setDataInicio(Calendar.getInstance());
        experienciaEmQuality = serviceCandidato
                .save(experienciaEmQuality);

        CategoriaCurso ensinoMedio = new CategoriaCurso();
        ensinoMedio.setNome("Ensino médio");
        ensinoMedio = serviceCategoriaCurso.save(ensinoMedio);

        CategoriaCurso ensinoFundamental = new CategoriaCurso();
        ensinoFundamental.setNome("Ensino Fundamental");
        ensinoFundamental = serviceCategoriaCurso.save(ensinoFundamental);

        CategoriaCurso ensinoSuperior = new CategoriaCurso();
        ensinoSuperior.setNome("Ensino Superior");
        ensinoSuperior = serviceCategoriaCurso.save(ensinoSuperior);

        CategoriaCurso tecnico = new CategoriaCurso();
        tecnico.setNome("Técnico");
        ensinoSuperior = serviceCategoriaCurso.save(tecnico);

        CategoriaCurso tecnologo = new CategoriaCurso();
        tecnologo.setNome("Técnólogo");
        tecnologo = serviceCategoriaCurso.save(tecnologo);

        CategoriaCurso profissionalizante = new CategoriaCurso();
        profissionalizante.setNome("Profissionalizante");
        profissionalizante = serviceCategoriaCurso.save(profissionalizante);

        CategoriaCurso especializacao = new CategoriaCurso();
        especializacao.setNome("Especialização");
        especializacao = serviceCategoriaCurso.save(especializacao);

        Curso mobile = new Curso();
        mobile.setNome("Curso Mobile Android");
        mobile.setDescricao("O Windows Mobile é o sistema operacional desenvolvido pela Microsoft para rodar em dispositivos móveis, como Pocket PCs Smartphones. Ele foi projetado para permitir que os usuários de dispositivos móveis possam realizar, em seus aparelhos, boa parte das tarefas que executariam em um PC. O Windows Mobile aciona dispositivos avançados e fáceis de usar, que permitem que você envie e receba e-mails, navegue na Internet e trabalhe em versões móveis dos já conhecidos aplicativos Microsoft Office. Com centenas de aplicativos disponíveis para expandir a plataforma, escolher quando, onde e como trabalhar só depende de você!");
        mobile.setCategoriaCurso(especializacao);
        mobile = serviceCurso.save(mobile);

        Curso cursoEnsinoFundamental = new Curso();
        cursoEnsinoFundamental.setNome("Ensino Fundamental");
        cursoEnsinoFundamental.setDescricao("Ensino fundamental");
        cursoEnsinoFundamental.setCategoriaCurso(ensinoFundamental);
        cursoEnsinoFundamental = serviceCurso.save(cursoEnsinoFundamental);

        Curso cursoEnsinoMedio = new Curso();
        cursoEnsinoMedio.setNome("Ensino médio");
        cursoEnsinoMedio.setDescricao("Normal médio");
        cursoEnsinoMedio.setCategoriaCurso(ensinoMedio);
        cursoEnsinoMedio = serviceCurso.save(cursoEnsinoMedio);

        Curso cursoProfissionalizanteTecnicoEmInformatica = new Curso();
        cursoProfissionalizanteTecnicoEmInformatica
                .setNome("Técnico em informática");
        cursoProfissionalizanteTecnicoEmInformatica
                .setDescricao("Montagem e manutenção em computadores");
        cursoProfissionalizanteTecnicoEmInformatica
                .setCategoriaCurso(profissionalizante);
        cursoProfissionalizanteTecnicoEmInformatica = serviceCurso
                .save(cursoProfissionalizanteTecnicoEmInformatica);

        Curso cursoProfissionalizanteTecnicoEmNotebooks = new Curso();
        cursoProfissionalizanteTecnicoEmNotebooks
                .setNome("Técnico em montagem e manutenção de notebooks");
        cursoProfissionalizanteTecnicoEmNotebooks
                .setDescricao("Montagem e manutenção de notebooks");
        cursoProfissionalizanteTecnicoEmNotebooks
                .setCategoriaCurso(profissionalizante);
        cursoProfissionalizanteTecnicoEmNotebooks = serviceCurso
                .save(cursoProfissionalizanteTecnicoEmNotebooks);

        Curso cursoProfissionalizanteWebDesigner = new Curso();
        cursoProfissionalizanteWebDesigner.setNome("WebDesigner");
        cursoProfissionalizanteWebDesigner
                .setDescricao("O curso de Sistemas de Informação tem como objetivo formar profissionais que contribuam para a solução de problemas de tratamento de informação, por meio da concepção, construção e manutenção de modelos informatizados de automação corporativa, apoiados nos conceitos e técnicas de informática, teoria de sistemas e administração. O campo profissional é amplo e diversificado e, nos últimos anos, às ocupações tradicionais como projeto, desenvolvimento e manutenção de sistemas computacionais na indústria, comércio, empresas de serviço ou instituições governamentais, somou-se a alternativa de atuar nas áreas de marketing e vendas de empresas. O currículo do curso busca formar profissionais empreendedores, capazes de analisar, projetar, desenvolver, implantarica, ética e em concordância com a evolução da informá");
        cursoProfissionalizanteWebDesigner.setCategoriaCurso(profissionalizante);
        cursoProfissionalizanteWebDesigner = serviceCurso
                .save(cursoProfissionalizanteWebDesigner);

        Curso sistemasDeInformacao = new Curso();
        sistemasDeInformacao.setNome("Sistemas de Informação");
        sistemasDeInformacao
                .setDescricao("O curso de Sistemas de Informação tem como objetivo formar profissionais que contribuam para a solução de problemas de tratamento de informação, por meio da concepção, construção e manutenção de modelos informatizados de automação corporativa, apoiados nos conceitos e técnicas de informática, teoria de sistemas e administração. O campo profissional é amplo e diversificado e, nos últimos anos, às ocupações tradicionais como projeto, desenvolvimento e manutenção de sistemas computacionais na indústria, comércio, empresas de serviço ou instituições governamentais, somou-se a alternativa de atuar nas áreas de marketing e vendas de empresas. O currículo do curso busca formar profissionais empreendedores, capazes de analisar, projetar, desenvolver, implantar e gerenciar sistemas de informação com visão crítica, ética e em concordância com a evolução da informática e suas aplicações. O curso é oferecido no período noturno e tem a duração de nove semestres.");
        sistemasDeInformacao.setCategoriaCurso(ensinoSuperior);
        sistemasDeInformacao = serviceCurso.save(sistemasDeInformacao);

        Curso pedagogia = new Curso();
        pedagogia.setNome("Pedagogia");
        pedagogia.setDescricao("Dar aulas e cuidar de quem da aulas");
        pedagogia.setCategoriaCurso(ensinoSuperior);
        pedagogia = serviceCurso.save(pedagogia);

        Curso administracao = new Curso();
        administracao.setNome("Administração");
        administracao.setDescricao("Administrar");
        administracao.setCategoriaCurso(ensinoSuperior);
        administracao = serviceCurso.save(administracao);

        CandidatoCurso emanuelMobile = new CandidatoCurso();
        emanuelMobile.setPeriodosConcluidos(3);
        emanuelMobile.setSituacaoDoCurso("trancado");
        emanuelMobile.setQuantidadeDePeriodos(6);
        emanuelMobile.setRegime("mensal");
        emanuelMobile
                .setOutrasInformacoes("t dispositivos móveis, como Pocket PCs Smartphones. Ele foi projetado para permitir que os usuários de dispositivos móveis possam realizar, em seus aparelhos, boa parte das tarefas que executariam em um PC. O Windows Mobile aciona dispositivos avançados e fáceis de usar, que permitem que você envie e receba e-mails, navegue na Internet e trabalhe em versões móveis dos já conhecidos aplicati quando, onde e como trabalhar só depende de você!");
        emanuelMobile.setCurso(mobile);
        emanuelMobile.setCandidato(emanuel);
        emanuelMobile.setInstituicaoDeEnsino(technosInformatica);
        emanuelMobile = serviceCandidato.save(emanuelMobile);

        CandidatoCurso emanuelEnsinoFundamental = new CandidatoCurso();
        emanuelEnsinoFundamental.setSituacaoDoCurso("concluido");
        emanuelEnsinoFundamental.setPeriodosConcluidos(8);
        emanuelEnsinoFundamental.setQuantidadeDePeriodos(8);
        emanuelEnsinoFundamental.setRegime("anual");
        emanuelEnsinoFundamental.setCurso(cursoEnsinoFundamental);
        emanuelEnsinoFundamental.setCandidato(emanuel);
        emanuelEnsinoFundamental
                .setInstituicaoDeEnsino(escolaMunicipalJoaoDaCostaViana);
        emanuelEnsinoFundamental = serviceCandidato
                .save(emanuelEnsinoFundamental);

        CandidatoCurso emanuelEnsinoMedio = new CandidatoCurso();
        emanuelEnsinoMedio.setSituacaoDoCurso("concluido");
        emanuelEnsinoMedio.setQuantidadeDePeriodos(3);
        emanuelEnsinoMedio.setPeriodosConcluidos(3);
        emanuelEnsinoMedio.setRegime("anual");
        emanuelEnsinoMedio.setCurso(cursoEnsinoMedio);
        emanuelEnsinoMedio.setCandidato(emanuel);
        emanuelEnsinoMedio
                .setInstituicaoDeEnsino(escolaEstadualArnaldoBusatto);
        emanuelEnsinoMedio = serviceCandidato.save(emanuelEnsinoMedio);

        CandidatoCurso emanuelFaculdade = new CandidatoCurso();
        emanuelFaculdade.setSituacaoDoCurso("cursando");
        emanuelFaculdade.setPeriodosConcluidos(8);
        emanuelFaculdade.setQuantidadeDePeriodos(8);
        emanuelFaculdade.setRegime("semestral");
        emanuelFaculdade
                .setOutrasInformacoes("O curso é oferecido no período noturno e tem a duração de nove semestres.");
        emanuelFaculdade.setPeriodosConcluidos(6);
        emanuelFaculdade.setCurso(sistemasDeInformacao);
        emanuelFaculdade.setCandidato(emanuel);
        emanuelFaculdade.setInstituicaoDeEnsino(udc);
        emanuelFaculdade = serviceCandidato.save(emanuelFaculdade);

        CandidatoCurso emanuelTecnicoEmInformatica = new CandidatoCurso();
        emanuelTecnicoEmInformatica.setSituacaoDoCurso("concluido");
        emanuelTecnicoEmInformatica.setQuantidadeDePeriodos(2);
        emanuelTecnicoEmInformatica.setPeriodosConcluidos(2);
        emanuelTecnicoEmInformatica.setRegime("mensal");
        emanuelTecnicoEmInformatica
                .setCurso(cursoProfissionalizanteTecnicoEmInformatica);
        emanuelTecnicoEmInformatica.setCandidato(emanuel);
        emanuelTecnicoEmInformatica
                .setInstituicaoDeEnsino(technosInformatica);
        emanuelTecnicoEmInformatica = serviceCandidato
                .save(emanuelTecnicoEmInformatica);

        CandidatoCurso emanuelTecnicoEmWebDesigner = new CandidatoCurso();
        emanuelTecnicoEmWebDesigner.setSituacaoDoCurso("concluido");
        emanuelTecnicoEmWebDesigner.setQuantidadeDePeriodos(2);
        emanuelTecnicoEmWebDesigner.setPeriodosConcluidos(2);
        emanuelTecnicoEmWebDesigner.setRegime("mensal");
        emanuelTecnicoEmWebDesigner
                .setCurso(cursoProfissionalizanteWebDesigner);
        emanuelTecnicoEmWebDesigner.setCandidato(emanuel);
        emanuelTecnicoEmWebDesigner
                .setInstituicaoDeEnsino(technosInformatica);
        emanuelTecnicoEmWebDesigner = serviceCandidato
                .save(emanuelTecnicoEmWebDesigner);

        CandidatoCurso emanuelTecnicoEmNotebooks = new CandidatoCurso();
        emanuelTecnicoEmNotebooks.setSituacaoDoCurso("concluido");
        emanuelTecnicoEmNotebooks.setQuantidadeDePeriodos(2);
        emanuelTecnicoEmNotebooks.setPeriodosConcluidos(2);
        emanuelTecnicoEmNotebooks.setRegime("mensal");
        emanuelTecnicoEmNotebooks
                .setCurso(cursoProfissionalizanteTecnicoEmNotebooks);
        emanuelTecnicoEmNotebooks.setCandidato(emanuel);
        emanuelTecnicoEmNotebooks
                .setInstituicaoDeEnsino(technosInformatica);
        emanuelTecnicoEmNotebooks = serviceCandidato
                .save(emanuelTecnicoEmNotebooks);

        CandidatoCurso sarahEnsinoFundamental = new CandidatoCurso();
        sarahEnsinoFundamental.setSituacaoDoCurso("concluido");
        sarahEnsinoFundamental.setQuantidadeDePeriodos(8);
        sarahEnsinoFundamental.setPeriodosConcluidos(8);
        sarahEnsinoFundamental.setRegime("anual");
        sarahEnsinoFundamental.setCurso(cursoEnsinoFundamental);
        sarahEnsinoFundamental.setCandidato(sarah);
        sarahEnsinoFundamental.setInstituicaoDeEnsino(escolaMunicipalJoaoDaCostaViana);
        sarahEnsinoFundamental = serviceCandidato.save(sarahEnsinoFundamental);

        CandidatoCurso sarahEnsinoMedio = new CandidatoCurso();
        sarahEnsinoMedio.setSituacaoDoCurso("concluido");
        sarahEnsinoMedio.setQuantidadeDePeriodos(3);
        sarahEnsinoMedio.setPeriodosConcluidos(3);
        sarahEnsinoMedio.setRegime("anual");
        sarahEnsinoMedio.setCurso(cursoEnsinoMedio);
        sarahEnsinoMedio.setCandidato(sarah);
        sarahEnsinoMedio.setInstituicaoDeEnsino(escolaEstadualArnaldoBusatto);
        sarahEnsinoMedio = serviceCandidato.save(sarahEnsinoMedio);

        CandidatoCurso sarahFaculdade = new CandidatoCurso();
        sarahFaculdade.setSituacaoDoCurso("concluido");
        sarahFaculdade.setQuantidadeDePeriodos(8);
        sarahFaculdade.setPeriodosConcluidos(8);
        sarahFaculdade.setRegime("semestral");
        sarahFaculdade.setCurso(pedagogia);
        sarahFaculdade.setCandidato(sarah);
        sarahFaculdade.setInstituicaoDeEnsino(udc);
        sarahFaculdade = serviceCandidato.save(sarahFaculdade);

        CandidatoCurso mayaraEnsinoFundamental = new CandidatoCurso();
        mayaraEnsinoFundamental.setSituacaoDoCurso("concluido");
        mayaraEnsinoFundamental.setQuantidadeDePeriodos(8);
        mayaraEnsinoFundamental.setPeriodosConcluidos(8);
        mayaraEnsinoFundamental.setRegime("anual");
        mayaraEnsinoFundamental.setCurso(cursoEnsinoFundamental);
        mayaraEnsinoFundamental.setCandidato(mayara);
        mayaraEnsinoFundamental.setInstituicaoDeEnsino(escolaMunicipalJoaoDaCostaViana);
        mayaraEnsinoFundamental = serviceCandidato.save(mayaraEnsinoFundamental);

        CandidatoCurso mayaraEnsinoMedio = new CandidatoCurso();
        mayaraEnsinoMedio.setSituacaoDoCurso("concluido");
        mayaraEnsinoMedio.setQuantidadeDePeriodos(3);
        mayaraEnsinoMedio.setPeriodosConcluidos(3);
        mayaraEnsinoMedio.setRegime("anual");
        mayaraEnsinoMedio.setCurso(cursoEnsinoMedio);
        mayaraEnsinoMedio.setCandidato(mayara);
        mayaraEnsinoMedio.setInstituicaoDeEnsino(escolaEstadualArnaldoBusatto);
        mayaraEnsinoMedio = serviceCandidato.save(mayaraEnsinoMedio);

        CandidatoCurso mayaraFaculdade = new CandidatoCurso();
        mayaraFaculdade.setSituacaoDoCurso("cursando");
        mayaraFaculdade.setPeriodosConcluidos(6);
        mayaraFaculdade.setQuantidadeDePeriodos(8);
        mayaraFaculdade.setRegime("semestral");
        mayaraFaculdade.setCurso(administracao);
        mayaraFaculdade.setCandidato(mayara);
        mayaraFaculdade.setInstituicaoDeEnsino(udc);
        mayaraFaculdade = serviceCandidato.save(mayaraFaculdade);

        Vaga analistaDeSistemasUDC = new Vaga();
        analistaDeSistemasUDC.setCargo(analistaDeSistemas);
        analistaDeSistemasUDC.setEmpresa(udc);
        analistaDeSistemasUDC.setEstadoCivil("casado");
        analistaDeSistemasUDC.setIdadeInicialDoCandidato(30);
        analistaDeSistemasUDC.setIdadeFinalDoCandidato(40);
        analistaDeSistemasUDC.setObservacoes("Deve ser homem, deve ter boa referência, deve estar preparado a sofrer pressão e blá blá bla blá");
        analistaDeSistemasUDC.setPreferenciaSexoMasculino("masculino");
        analistaDeSistemasUDC.setSalario(7000.00);
        analistaDeSistemasUDC.setBairro(bairro);
        analistaDeSistemasUDC = serviceVaga.save(analistaDeSistemasUDC);

        Vaga pedagogoArnaldoBusatto = new Vaga();
        pedagogoArnaldoBusatto.setCargo(pedagogo);
        pedagogoArnaldoBusatto.setEmpresa(escolaEstadualArnaldoBusatto);
        pedagogoArnaldoBusatto.setEstadoCivil("casado");
        pedagogoArnaldoBusatto.setIdadeInicialDoCandidato(30);
        pedagogoArnaldoBusatto.setIdadeFinalDoCandidato(40);
        pedagogoArnaldoBusatto.setObservacoes("deve ter disponibilidade em várias horários e blá blá blá");
        pedagogoArnaldoBusatto.setBairro(bairro);
        pedagogoArnaldoBusatto.setPreferenciaSexoMasculino("feminino");
        pedagogoArnaldoBusatto.setSalario(4000.00);
        pedagogoArnaldoBusatto = serviceVaga.save(pedagogoArnaldoBusatto);

        Encaminhamento encaminhamentoSarah = new Encaminhamento();
        encaminhamentoSarah.setCandidato(sarah);
        encaminhamentoSarah.setDataDeCadastro(Calendar.getInstance());
        encaminhamentoSarah.setVaga(pedagogoArnaldoBusatto);
        encaminhamentoSarah = serviceEncaminhamento.save(encaminhamentoSarah);


        assertTrue(true);

    }


}
