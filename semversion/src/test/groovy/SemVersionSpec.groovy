import spock.lang.Specification
import spock.lang.Unroll

class SemVersionSpec extends Specification {

    VersionManager versionManager = new VersionManager()

    def "canary test"() {
        expect:
        true == true
    }

    @Unroll
    def "Should return #expectedMessage for old version #installedVersion and current version #currentVersion"() {
        given:
        installedVersion
        currentVersion

        when:
        String message = versionManager.upgrade(installedVersion, currentVersion)

        then:
        expectedMessage == message

        where:
        installedVersion | currentVersion | expectedMessage
        '1.0.0'          | '1.0.1'        | 'Upgrading from 1.0.0 to 1.0.1'
        '1.0.1'          | '1.0.0'        | 'Downgrading from 1.0.1 to 1.0.0'
        '1.0.0'          | '1.1.0'        | 'Upgrading from 1.0.0 to 1.1.0'
        '1.1.1'          | '1.1.1'        | 'You are up to date'
        '1.9.0'          | '1.10.0'       | 'Upgrading from 1.9.0 to 1.10.0'
        '1.9.0'          | '2.0.1'        | 'Upgrading from 1.9.0 to 2.0.1'
        '2.1.10'         | '2.1.9'        | 'Downgrading from 2.1.10 to 2.1.9'
        '2.2.0'          | '2.3.0'        | 'Upgrading from 2.2.0 to 2.3.0'
        '2.4.0'          | '2.3.0'        | 'Downgrading from 2.4.0 to 2.3.0'
    }
}