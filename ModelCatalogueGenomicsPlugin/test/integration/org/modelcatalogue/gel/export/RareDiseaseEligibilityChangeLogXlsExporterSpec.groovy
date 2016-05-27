package org.modelcatalogue.gel.export

import org.modelcatalogue.core.DataClass

import static junit.framework.Assert.assertEquals
import static org.modelcatalogue.core.util.test.FileOpener.open

/**
 * Test for RD eligibility criteria change log reports class using test model with similar format to expected data.
 */
class RareDiseaseEligibilityChangeLogXlsExporterSpec extends AbstractRareDiseasesExporterSpec {


    def "Export rare disease eligibility change logs to xls"() {
        when:
        buildTestModel(true)
        findDataIds()
        DataClass dataClass = DataClass.findByName('Dataclass Top Level 1 Root')
        makeChanges(dataClass)

        //test generated content
        RareDiseaseEligibilityChangeLogXlsExporter exporter = new RareDiseaseEligibilityChangeLogXlsExporter(auditService, dataClassService, sessionFactory, 5, false)
        List<String> rows = exporter.buildContentRows(dataClass)

        //need to prime with header as we're not running the excel part of the exporter here
        String allRows = 'Change reference,Level 2 Disease Group (ID),Level 3 Disease Subtype (ID),Level 4 Specific Disorder (ID),Affected Data Item,Change Type,Current version details,New version details\n'

        rows.each{ row ->
            allRows+= "${row.join(',')}\n"
        }

        println "EXPECTED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<"
        println expectedRDPhenotypesCsv
        println ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"
        println "GOT<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<"
        println allRows
        println ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"


        File file = temporaryFolder.newFile("${System.currentTimeMillis()}.xlsx")
        //test excel generation
        new RareDiseaseEligibilityChangeLogXlsExporter(auditService, dataClassService, sessionFactory, 5, false).export(dataClass, file.newOutputStream())

        open file

        then:
        noExceptionThrown()
        assertEquals(expectedRDPhenotypesCsv, allRows) //need to use junit assert as Spock blows up badly on failure (OutOfMem)
    }

    private String getExpectedRDPhenotypesCsv() {
        return """Change reference,Level 2 Disease Group (ID),Level 3 Disease Subtype (ID),Level 4 Specific Disorder (ID),Affected Data Item,Change Type,Current version details,New version details
,Disorder >>>1<<< Level2 ($level2_id_1),Disorder >>>1<<< SubCondition Level3 Model Data Element 1 ($level3_id_1),Disorder >>1<< heading Level4 Model Data Element 1 ($level4_id_1),Disorder >1< Eligibility Level5 Model 1 Data Element 1 ($level5_id_1),New Data Item,,Disorder >1< Eligibility Level5 Model 1 Data Element 1 ($level5_id_1)
,Disorder >>>1<<< Level2 ($level2_id_1),Disorder >>>1<<< SubCondition Level3 Model Data Element 1 ($level3_id_1),Disorder >>1<< heading Level4 Model Data Element 1 ($level4_id_1),Inclusion criteria name 1 1 ($level6_inclusion_1),New Data Item,,Inclusion criteria name 1 1 ($level6_inclusion_1)
,Disorder >>>1<<< Level2 ($level2_id_1),Disorder >>>1<<< SubCondition Level3 Model Data Element 1 ($level3_id_1),Disorder >>1<< heading Level4 Model Data Element 1 ($level4_id_1),Exclusion criteria name 1 1 ($level6_exclusion_1),New Data Item,,Exclusion criteria name 1 1 ($level6_exclusion_1)
,Disorder >>>1<<< Level2 ($level2_id_1),Disorder >>>1<<< SubCondition Level3 Model Data Element 1 ($level3_id_1),Disorder >>1<< heading Level4 Model Data Element 1 ($level4_id_1),Prior Genetic testing name 1 1 ($level6_priorGenetic_1),New Data Item,,Prior Genetic testing name 1 1 ($level6_priorGenetic_1)
,Disorder >>>1<<< Level2 ($level2_id_1),Disorder >>>1<<< SubCondition Level3 Model Data Element 1 ($level3_id_1),Disorder >>1<< heading Level4 Model Data Element 1 ($level4_id_1),Prior testing genes name 1 1 ($level6_prior_genes_1),New Data Item,,Prior testing genes name 1 1 ($level6_prior_genes_1)
,Disorder >>>1<<< Level2 ($level2_id_1),Disorder >>>1<<< SubCondition Level3 Model Data Element 1 ($level3_id_1),Disorder >>1<< heading Level4 Model Data Element 1 ($level4_id_1),Closing statement name 1 1 ($level6_closing_1),New Data Item,,Closing statement name 1 1 ($level6_closing_1)
,Disorder >>>1<<< Level2 ($level2_id_1),Disorder >>>1<<< SubCondition Level3 Model Data Element 1 ($level3_id_1),Disorder >>1<< heading Level4 Model Data Element 1 ($level4_id_1),Guidance name 1 1 ($level6_guidance_1),New Data Item,,Guidance name 1 1 ($level6_guidance_1)
,Disorder >>>1<<< Level2 ($level2_id_1),Disorder >>>1<<< SubCondition Level3 Model Data Element 2 ($level3_id_2),Disorder >>1<< heading Level4 Model Data Element 2 ($level4_id_2),Disorder >1< Eligibility Level5 Model 1 Data Element 2 ($level5_id_2),New Data Item,,Disorder >1< Eligibility Level5 Model 1 Data Element 2 ($level5_id_2)
,Disorder >>>1<<< Level2 ($level2_id_1),Disorder >>>1<<< SubCondition Level3 Model Data Element 2 ($level3_id_2),Disorder >>1<< heading Level4 Model Data Element 2 ($level4_id_2),Inclusion criteria name 1 2 ($level6_inclusion_2),New Data Item,,Inclusion criteria name 1 2 ($level6_inclusion_2)
,Disorder >>>1<<< Level2 ($level2_id_1),Disorder >>>1<<< SubCondition Level3 Model Data Element 2 ($level3_id_2),Disorder >>1<< heading Level4 Model Data Element 2 ($level4_id_2),Exclusion criteria name 1 2 ($level6_exclusion_2),New Data Item,,Exclusion criteria name 1 2 ($level6_exclusion_2)
,Disorder >>>1<<< Level2 ($level2_id_1),Disorder >>>1<<< SubCondition Level3 Model Data Element 2 ($level3_id_2),Disorder >>1<< heading Level4 Model Data Element 2 ($level4_id_2),Prior Genetic testing name 1 2 ($level6_priorGenetic_2),New Data Item,,Prior Genetic testing name 1 2 ($level6_priorGenetic_2)
,Disorder >>>1<<< Level2 ($level2_id_1),Disorder >>>1<<< SubCondition Level3 Model Data Element 2 ($level3_id_2),Disorder >>1<< heading Level4 Model Data Element 2 ($level4_id_2),Prior testing genes name 1 2 ($level6_prior_genes_2),New Data Item,,Prior testing genes name 1 2 ($level6_prior_genes_2)
,Disorder >>>1<<< Level2 ($level2_id_1),Disorder >>>1<<< SubCondition Level3 Model Data Element 2 ($level3_id_2),Disorder >>1<< heading Level4 Model Data Element 2 ($level4_id_2),Closing statement name 1 2 ($level6_closing_2),New Data Item,,Closing statement name 1 2 ($level6_closing_2)
,Disorder >>>1<<< Level2 ($level2_id_1),Disorder >>>1<<< SubCondition Level3 Model Data Element 2 ($level3_id_2),Disorder >>1<< heading Level4 Model Data Element 2 ($level4_id_2),Guidance name 1 2 ($level6_guidance_2),New Data Item,,Guidance name 1 2 ($level6_guidance_2)
,Disorder >>>2<<< Level2 ($level2_id_2),Disorder >>>2<<< SubCondition Level3 Model Data Element 1 ($level3_id_3),Disorder >>2<< heading Level4 Model Data Element 1 ($level4_id_3),Disorder >2< Eligibility Level5 Model 2 Data Element 1 ($level5_id_3),New Data Item,,Disorder >2< Eligibility Level5 Model 2 Data Element 1 ($level5_id_3)
,Disorder >>>2<<< Level2 ($level2_id_2),Disorder >>>2<<< SubCondition Level3 Model Data Element 1 ($level3_id_3),Disorder >>2<< heading Level4 Model Data Element 1 ($level4_id_3),Inclusion criteria name 2 1 ($level6_inclusion_3),New Data Item,,Inclusion criteria name 2 1 ($level6_inclusion_3)
,Disorder >>>2<<< Level2 ($level2_id_2),Disorder >>>2<<< SubCondition Level3 Model Data Element 1 ($level3_id_3),Disorder >>2<< heading Level4 Model Data Element 1 ($level4_id_3),Exclusion criteria name 2 1 ($level6_exclusion_3),New Data Item,,Exclusion criteria name 2 1 ($level6_exclusion_3)
,Disorder >>>2<<< Level2 ($level2_id_2),Disorder >>>2<<< SubCondition Level3 Model Data Element 1 ($level3_id_3),Disorder >>2<< heading Level4 Model Data Element 1 ($level4_id_3),Prior Genetic testing name 2 1 ($level6_priorGenetic_3),New Data Item,,Prior Genetic testing name 2 1 ($level6_priorGenetic_3)
,Disorder >>>2<<< Level2 ($level2_id_2),Disorder >>>2<<< SubCondition Level3 Model Data Element 1 ($level3_id_3),Disorder >>2<< heading Level4 Model Data Element 1 ($level4_id_3),Prior testing genes name 2 1 ($level6_prior_genes_3),New Data Item,,Prior testing genes name 2 1 ($level6_prior_genes_3)
,Disorder >>>2<<< Level2 ($level2_id_2),Disorder >>>2<<< SubCondition Level3 Model Data Element 1 ($level3_id_3),Disorder >>2<< heading Level4 Model Data Element 1 ($level4_id_3),Closing statement name 2 1 ($level6_closing_3),New Data Item,,Closing statement name 2 1 ($level6_closing_3)
,Disorder >>>2<<< Level2 ($level2_id_2),Disorder >>>2<<< SubCondition Level3 Model Data Element 1 ($level3_id_3),Disorder >>2<< heading Level4 Model Data Element 1 ($level4_id_3),Guidance name 2 1 ($level6_guidance_3),New Data Item,,Guidance name 2 1 ($level6_guidance_3)
,Disorder >>>2<<< Level2 ($level2_id_2),Disorder >>>2<<< SubCondition Level3 Model Data Element 2 ($level3_id_4),Disorder >>2<< heading Level4 Model Data Element 2 ($level4_id_4),Disorder >2< Eligibility Level5 Model 2 Data Element 2 ($level5_id_4),New Data Item,,Disorder >2< Eligibility Level5 Model 2 Data Element 2 ($level5_id_4)
,Disorder >>>2<<< Level2 ($level2_id_2),Disorder >>>2<<< SubCondition Level3 Model Data Element 2 ($level3_id_4),Disorder >>2<< heading Level4 Model Data Element 2 ($level4_id_4),Inclusion criteria name 2 2 ($level6_inclusion_4),New Data Item,,Inclusion criteria name 2 2 ($level6_inclusion_4)
,Disorder >>>2<<< Level2 ($level2_id_2),Disorder >>>2<<< SubCondition Level3 Model Data Element 2 ($level3_id_4),Disorder >>2<< heading Level4 Model Data Element 2 ($level4_id_4),Exclusion criteria name 2 2 ($level6_exclusion_4),New Data Item,,Exclusion criteria name 2 2 ($level6_exclusion_4)
,Disorder >>>2<<< Level2 ($level2_id_2),Disorder >>>2<<< SubCondition Level3 Model Data Element 2 ($level3_id_4),Disorder >>2<< heading Level4 Model Data Element 2 ($level4_id_4),Prior Genetic testing name 2 2 ($level6_priorGenetic_4),New Data Item,,Prior Genetic testing name 2 2 ($level6_priorGenetic_4)
,Disorder >>>2<<< Level2 ($level2_id_2),Disorder >>>2<<< SubCondition Level3 Model Data Element 2 ($level3_id_4),Disorder >>2<< heading Level4 Model Data Element 2 ($level4_id_4),Prior testing genes name 2 2 ($level6_prior_genes_4),New Data Item,,Prior testing genes name 2 2 ($level6_prior_genes_4)
,Disorder >>>2<<< Level2 ($level2_id_2),Disorder >>>2<<< SubCondition Level3 Model Data Element 2 ($level3_id_4),Disorder >>2<< heading Level4 Model Data Element 2 ($level4_id_4),Closing statement name 2 2 ($level6_closing_4),New Data Item,,Closing statement name 2 2 ($level6_closing_4)
,Disorder >>>2<<< Level2 ($level2_id_2),Disorder >>>2<<< SubCondition Level3 Model Data Element 2 ($level3_id_4),Disorder >>2<< heading Level4 Model Data Element 2 ($level4_id_4),Guidance name 2 2 ($level6_guidance_4),New Data Item,,Guidance name 2 2 ($level6_guidance_4)
"""
    }

}
