# Multi-mode resource-constrained project scheduling

This repository provides the implementation which was used to generate the computational results presented in the paper
> __*Continuous-time formulations for multi-mode project scheduling*__ [![DOI:10.1007/978-3-031-08623-6_51](https://img.shields.io/static/v1?label=DOI&message=10.1016%2Fj.cor.2023.106147&color=blue)](https://doi.org/10.1016/j.cor.2023.106147)
 
 by <a href="https://orcid.org/0000-0002-8977-9414">me<img  src="https://orcid.org/sites/default/files/images/orcid_16x16.png" style="width:1em;margin-left:.3em;"></a>.

Anyone can __download__ the published article for __free__ by  clicking the link 

> [https://authors.elsevier.com/a/1gTYX15N8SNAWo](https://authors.elsevier.com/a/1gTYX15N8SNAWo)

before March 14, 2023. 

A preprint version is also available on [arxiv.org](https://arxiv.org/). [![arXiv](https://img.shields.io/badge/arXiv-2301.04700-b31b1b.svg)](http://arxiv.org/abs/2301.04700)

## Download Benchmark Instances

If you are interested to download the benchmark data sets that I used in my work, simply follow this [link](https://minhaskamal.github.io/DownGit/#/home?url=https://github.com/ddotsdot/project-scheduling-code/tree/master/src/test/resources/instances&fileName=datasets&rootDirectory=false) to get a zip-archive with all data sets.
In case you want to use any of my derived data sets (`c15_d, j20_d, J50_d`) in your own work, please _cite this paper_ ([BibTeX](https://www.doi2bib.org/bib/10.1016/j.cor.2023.106147)).  

## Optimization Tools

* __*jDecOR*__ ([0.4.0](https://maven.optimal-solution.org/service/rest/repository/browse/releases/org/optsol/jdecor/)) <br>
  This project is based on the [jdecor-pojo-template](https://github.com/OPTIMAL-SOLUTION-org/jdecor-pojo-template). __*jDecOR*__ framework helps to focus on the mathematical formulation and keeps boilerplate of mixed-integer programming at a minimum level.
* __*Google OR-Tools*__ ([9.3.0](https://developers.google.com/optimization/support/release_notes))
* __*SCIP*__ ([8.0.0](https://www.scipopt.org/doc-7.0.1/html/))

## Prerequisites

* Java 11
* Maven 3
* Lombok
