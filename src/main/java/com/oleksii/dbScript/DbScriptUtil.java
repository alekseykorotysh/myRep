package com.oleksii.dbScript;

public class DbScriptUtil {

    private DbScriptUtil() {

    }

    public static String getScript() {
        return "DROP TABLE IF EXISTS KEYS;\n" +
                "CREATE TABLE KEYS (\n" +
                "id INT AUTO_INCREMENT,\n" +
                "key_word VARCHAR(255),\n" +
                "PRIMARY KEY (id));\n" +
                "\n" +
                "INSERT INTO KEYS(Key_word)\n" +
                "VALUES('contract'),\n" +
                "('producer'),\n" +
                "('consumer'), \n" +
                "('big test'),\n" +
                "('pipeline'), \n" +
                "('pipe line'), \n" +
                "('deployment'), \n" +
                "('sdd'),\n" +
                "('hld'), \n" +
                "('automation'), \n" +
                "('test run'), \n" +
                "('stage'), \n" +
                "('preprod'), \n" +
                "('preprod enviroment'), \n" +
                "('enviroment'), \n" +
                "('prod'), \n" +
                "('prod enviroment'), \n" +
                "('gatekeeper'), \n" +
                "('swagger'), \n" +
                "('contract messaging'), \n" +
                "('contract rest'), \n" +
                "('changelog'), \n" +
                "('health check'), \n" +
                "('add health check'), \n" +
                "('qa tools'), \n" +
                "('device emulator'), \n" +
                "('add lo4j'), \n" +
                "('add metadata log4j'), \n" +
                "('metadata'), \n" +
                "('logging'), \n" +
                "('segmentation'), \n" +
                "('segment'), \n" +
                "('feature'), \n" +
                "('processes'), \n" +
                "('service'), \n" +
                "('kafka'), \n" +
                "('bi'), \n" +
                "('segments'), \n" +
                "('bingo specific regulator'), \n" +
                "('add kafka'), \n" +
                "('add kafka topic'), \n" +
                "('create kafka'), \n" +
                "('create messaging'), \n" +
                "('create service'), \n" +
                "('new service'), \n" +
                "('add new service'), \n" +
                "('add service');\n" +
                "\n" +
                "\n" +
                "DROP TABLE IF EXISTS QUERIES;\n" +
                "CREATE TABLE QUERIES (\n" +
                "id INT AUTO_INCREMENT,\n" +
                "key_id INT,\n" +
                "link_names TEXT,\n" +
                "PRIMARY KEY (id),\n" +
                "FOREIGN KEY (key_id) REFERENCES KEYS (id));\n" +
                "\n" +
                "INSERT INTO QUERIES (key_id, link_names)\n" +
                "VALUES\n" +
                "('1','https://wiki.playtika.com/display/~schereshnyuk/Contract+testing+or+Consumer+Driven+Contract,https://wiki.playtika" +
                ".com/display/SMTA/Development+Workflow+with+Spring+Cloud+Contract,https://wiki.playtika.com/pages/viewpage" +
                ".action?spaceKey=QAKB&title=Contract+tests+workflow'),\n" +
                "('2','https://wiki.playtika.com/display/~schereshnyuk/Contract+testing+or+Consumer+Driven+Contract,https://wiki.playtika" +
                ".com/display/SMTA/Development+Workflow+with+Spring+Cloud+Contract,https://wiki.playtika.com/pages/viewpage" +
                ".action?spaceKey=QAKB&title=Contract+tests+workflow'),\n" +
                "('3','https://wiki.playtika.com/display/~schereshnyuk/Contract+testing+or+Consumer+Driven+Contract,https://wiki.playtika" +
                ".com/display/SMTA/Development+Workflow+with+Spring+Cloud+Contract,https://wiki.playtika.com/pages/viewpage" +
                ".action?spaceKey=QAKB&title=Contract+tests+workflow'),\n" +
                "('4','https://wiki.playtika.com/display/SMTA/Big+tests+code+guidelines,https://wiki.playtika" +
                ".com/display/QAKB/Test+Automation+for+Microservices'),\n" +
                "('5','https://wiki.playtika.com/display/Automation/Caesars+2.0,https://wiki.playtika.com/display/Automation/Pipeline+1.7,https://wiki" +
                ".playtika.com/display/Automation/Pipeline+1.0,https://wiki.playtika.com/pages/viewpage" +
                ".action?spaceKey=INFRA&title=Migration+to+Pipeline+1.7+-+2.0'),\n" +
                "('6','https://wiki.playtika.com/display/Automation/Caesars+2.0,https://wiki.playtika.com/display/Automation/Pipeline+1.7,https://wiki" +
                ".playtika.com/display/Automation/Pipeline+1.0,https://wiki.playtika.com/pages/viewpage" +
                ".action?spaceKey=INFRA&title=Migration+to+Pipeline+1.7+-+2.0'),\n" +
                "('7','https://wiki.playtika.com/display/Automation/Caesars+2.0,https://wiki.playtika.com/display/Automation/Pipeline+1.7,https://wiki" +
                ".playtika.com/display/Automation/Pipeline+1.0,https://wiki.playtika.com/pages/viewpage" +
                ".action?spaceKey=INFRA&title=Migration+to+Pipeline+1.7+-+2.0'),\n" +
                "('8','https://wiki.playtika.com/display/INFRA/Boost+Service+SDDhttps://wiki.playtika.com/display/WSOP/WSOP+SDD,https://wiki.playtika" +
                ".com/display/CC/Crafting+Service+SDD, https://wiki.playtika.com/display/CC/Dragon+Run+SDD,https://wiki.playtika" +
                ".com/display/INFRA/Registration+Service+Backend+SDD'),\n" +
                "('9','https://wiki.playtika.com/display/CC/Caesar%27s+Show+HLD,https://wiki.playtika.com/display/CC/The+Four+Elements+HLD,https://wiki" +
                ".playtika.com/display/CC/Joker+HLD ,https://wiki.playtika.com/display/CC/Memory+game+HLD'),\n" +
                "('10','https://wiki.playtika.com/pages/viewpage.action?spaceKey=QAKB&title=CC+Test+Automation+FAQ,https://wiki.playtika.com/pages/viewpage" +
                ".action?pageId=114866324 ,https://wiki.playtika.com/pages/viewpage" +
                ".action?spaceKey=QAKB&title=How+to+create+Test+run+for+weekly+mobile+version,https://wiki.playtika.com/display/BBB/API+Tests'),\n" +
                "('11','https://wiki.playtika.com/pages/viewpage.action?spaceKey=QAKB&title=CC+Test+Automation+FAQ,https://wiki.playtika.com/pages/viewpage" +
                ".action?pageId=114866324 ,https://wiki.playtika.com/pages/viewpage" +
                ".action?spaceKey=QAKB&title=How+to+create+Test+run+for+weekly+mobile+version,https://wiki.playtika.com/display/BBB/API+Tests'),\n" +
                "('12','https://wiki.playtika.com/display/dev/Stage+Problems'),\n" +
                "('13', 'https://wiki.playtika.com/display/IT/Casino+Preprod,https://wiki.playtika.com/display/IT/Slotomania+Preprod'),\n" +
                "('14', 'https://wiki.playtika.com/display/IT/Casino+Preprod,https://wiki.playtika.com/display/IT/Slotomania+Preprod'),\n" +
                "('15', 'https://wiki.playtika.com/display/IT/Casino+Preprod,https://wiki.playtika.com/display/IT/Slotomania+Preprod'),\n" +
                "('16','https://wiki.playtika.com/display/IT/Casino+Prod+VA2'),\n" +
                "('17','https://wiki.playtika.com/display/IT/Casino+Prod+VA2'),\n" +
                "('18','https://wiki.playtika.com/display/INFRA/Services+Gatekeepers+v.2,https://wiki.playtika.com/display/SLOT/SM+Services+-+Gatekeepers')," +
                "\n" +
                "('19','https://wiki.playtika.com/pages/viewpage.action?pageId=117968090'),\n" +
                "('20','https://wiki.playtika.com/pages/viewpage.action?pageId=114201756'),\n" +
                "('21','https://wiki.playtika.com/display/INFRA/How+to+add+and+fill+changelog+for+service'),\n" +
                "('22','https://wiki.playtika.com/display/INFRA/How+to+add+Health+Check+for+a+service'),\n" +
                "('23','https://wiki.playtika.com/display/INFRA/How+to+add+Health+Check+for+a+service'),\n" +
                "('24','https://wiki.playtika.com/display/INFRA/How+to+add+Health+Check+for+a+service'),\n" +
                "('25','https://wiki.playtika.com/display/INFRA/How+to+add+Health+Check+for+a+service'),\n" +
                "('26','https://wiki.playtika.com/display/BBB/Create+Facebook+Test+Users+via+Swagger+and+Bingo+REST+Service, https://wiki.playtika" +
                ".com/display/QAKB/How+to+install+Device+Emulator, https://wiki.playtika.com/display/INFRA/How+to+run+device+emulator+on+Mac'),\n" +
                "('27','https://wiki.playtika.com/display/BBB/Create+Facebook+Test+Users+via+Swagger+and+Bingo+REST+Service, https://wiki.playtika" +
                ".com/display/QAKB/How+to+install+Device+Emulator, https://wiki.playtika.com/display/INFRA/How+to+run+device+emulator+on+Mac'),\n" +
                "('28','https://wiki.playtika.com/display/INFRA/How+to+add+log4j+metadata+logging+to+your+service'),\n" +
                "('29','https://wiki.playtika.com/display/INFRA/How+to+add+log4j+metadata+logging+to+your+service'),\n" +
                "('29','https://wiki.playtika.com/display/INFRA/How+to+add+log4j+metadata+logging+to+your+service'),\n" +
                "('30','https://wiki.playtika.com/display/INFRA/How+to+add+log4j+metadata+logging+to+your+service'),\n" +
                "('31','https://wiki.playtika.com/display/INFRA/How+to+add+log4j+metadata+logging+to+your+service'),\n" +
                "('32','https://wiki.playtika.com/display/INFRA/How+to+add+new+feature+segment+in+Segmentation+2.0'),\n" +
                "('33','https://wiki.playtika.com/display/INFRA/How+to+add+new+feature+segment+in+Segmentation+2.0'),\n" +
                "('34','https://wiki.playtika.com/display/INFRA/How+to+add+new+feature+segment+in+Segmentation+2.0'),\n" +
                "('35','https://wiki.playtika.com/display/BBB/BingoBlitz+Processes'),\n" +
                "('36','https://wiki.playtika.com/display/INFRA/How+to+add+new+service+or+change+existing+in+monitoring+systems'),\n" +
                "('37','https://wiki.playtika.com/display/INFRA/How+to+add+new+service+or+change+existing+in+monitoring+systems'),\n" +
                "('38','https://wiki.playtika.com/display/BBB/Kafka+guidelines'),\n" +
                "('39','https://wiki.playtika.com/display/INFRA/How+to+correctly+create+bi+segments'),\n" +
                "('40','https://wiki.playtika.com/display/INFRA/How+to+correctly+create+bi+segments'),\n" +
                "('41','https://wiki.playtika.com/display/INFRA/How+to+correctly+create+bi+segments'),\n" +
                "('42','https://wiki.playtika.com/display/BBB/Regulator'), \n" +
                "('43','https://wiki.playtika.com/display/INFRA/How+to+create+a+New+Kafka+Topic'),\n" +
                "('44','https://wiki.playtika.com/display/INFRA/How+to+create+a+New+Kafka+Topic'),\n" +
                "('45','https://wiki.playtika.com/display/INFRA/How+to+create+a+New+Kafka+Topic'),\n" +
                "('46','https://wiki.playtika.com/display/INFRA/How+to+create+a+New+Kafka+Topic'),\n" +
                "('47','https://wiki.playtika.com/display/INFRA/How+to+create+a+New+Kafka+Topic');";
    }
}
