package io.github.patternatlas.auth.defaultSetup;

import io.github.patternatlas.auth.user.entities.*;
import io.github.patternatlas.auth.user.repositories.PrivilegeRepository;
import io.github.patternatlas.auth.user.repositories.RoleRepository;
import io.github.patternatlas.auth.user.repositories.UserRepository;
import io.github.patternatlas.auth.pattern.repositories.PatternLanguageRepository;
import io.github.patternatlas.auth.pattern.repositories.PatternRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collection;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;
    Logger logger = LoggerFactory.getLogger(SetupDataLoader.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Autowired
    private PatternLanguageRepository patternLanguageRepository;

    @Autowired
    private PatternRepository patternRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        this.userRepository.findAll().stream().forEach(user -> logger.info(user.getEmail()));
        /** PATTERN LANGUAGE */
        /*this.patternLanguageRepository.findAll().stream().forEach(patternLanguage -> {
            createPrivilegeIfNotFound("PATTERN_LANGUAGE_READ_" + patternLanguage.getId().toString());
            createPrivilegeIfNotFound("PATTERN_LANGUAGE_EDIT_" + patternLanguage.getId().toString());
            createPrivilegeIfNotFound("PATTERN_LANGUAGE_DELETE_" + patternLanguage.getId().toString());
        });*/
        /** PATTERN */
        /*this.patternRepository.findAll().stream().forEach(pattern -> {
            createPrivilegeIfNotFound("APPROVED_PATTERN_READ_" + pattern.getId().toString());
            createPrivilegeIfNotFound("APPROVED_PATTERN_EDIT_" + pattern.getId().toString());
            createPrivilegeIfNotFound("APPROVED_PATTERN_DELETE_" + pattern.getId().toString());
        });*/

        if (alreadySetup)
            return;

        /** Privileges */
        /** ISSUE */
        Privilege readIssuePrivilege = createPrivilegeIfNotFound(PrivilegeConstant.ISSUE_READ);
        Privilege updateIssuePrivilege = createPrivilegeIfNotFound(PrivilegeConstant.ISSUE_EDIT);
        Privilege deleteIssuePrivilege = createPrivilegeIfNotFound(PrivilegeConstant.ISSUE_DELETE);
        Privilege commentIssuePrivilege = createPrivilegeIfNotFound(PrivilegeConstant.ISSUE_COMMENT);
        Privilege voteIssuePrivilege = createPrivilegeIfNotFound(PrivilegeConstant.ISSUE_VOTE);
        Privilege evidenceIssuePrivilege = createPrivilegeIfNotFound(PrivilegeConstant.ISSUE_EVIDENCE);
        Privilege toPatternCandidate = createPrivilegeIfNotFound(PrivilegeConstant.ISSUE_TO_PATTERN_CANDIDATE);
        Privilege createIssuePrivilege = createPrivilegeIfNotFound(PrivilegeConstant.ISSUE_CREATE);
        Privilege readIssuePrivilegeAll = createPrivilegeIfNotFound(PrivilegeConstant.ISSUE_READ_ALL);
        Privilege updateIssuePrivilegeAll = createPrivilegeIfNotFound(PrivilegeConstant.ISSUE_EDIT_ALL);
        Privilege deleteIssuePrivilegeAll = createPrivilegeIfNotFound(PrivilegeConstant.ISSUE_DELETE_ALL);
        Privilege commentIssuePrivilegeAll = createPrivilegeIfNotFound(PrivilegeConstant.ISSUE_COMMENT_ALL);
        Privilege voteIssuePrivilegeAll = createPrivilegeIfNotFound(PrivilegeConstant.ISSUE_VOTE_ALL);
        Privilege evidenceIssuePrivilegeAll = createPrivilegeIfNotFound(PrivilegeConstant.ISSUE_EVIDENCE_ALL);
        Privilege toPatternCandidateAll = createPrivilegeIfNotFound(PrivilegeConstant.ISSUE_TO_PATTERN_CANDIDATE_ALL);
        /** CANDIDATE */
        Privilege readCandidatePrivilege = createPrivilegeIfNotFound(PrivilegeConstant.PATTERN_CANDIDATE_READ);
        Privilege updateCandidatePrivilege = createPrivilegeIfNotFound(PrivilegeConstant.PATTERN_CANDIDATE_EDIT);
        Privilege deleteCandidatePrivilege = createPrivilegeIfNotFound(PrivilegeConstant.PATTERN_CANDIDATE_DELETE);
        Privilege commentCandidatePrivilege = createPrivilegeIfNotFound(PrivilegeConstant.PATTERN_CANDIDATE_COMMENT);
        Privilege voteCandidatePrivilege = createPrivilegeIfNotFound(PrivilegeConstant.PATTERN_CANDIDATE_VOTE);
        Privilege evidenceCandidatePrivilege = createPrivilegeIfNotFound(PrivilegeConstant.PATTERN_CANDIDATE_EVIDENCE);
        Privilege toApprovedPattern = createPrivilegeIfNotFound(PrivilegeConstant.PATTERN_CANDIDATE_TO_PATTERN);
        Privilege createCandidatePrivilege = createPrivilegeIfNotFound(PrivilegeConstant.PATTERN_CANDIDATE_CREATE);
        Privilege readCandidatePrivilegeAll = createPrivilegeIfNotFound(PrivilegeConstant.PATTERN_CANDIDATE_READ_ALL);
        Privilege updateCandidatePrivilegeAll = createPrivilegeIfNotFound(PrivilegeConstant.PATTERN_CANDIDATE_EDIT_ALL);
        Privilege deleteCandidatePrivilegeAll = createPrivilegeIfNotFound(PrivilegeConstant.PATTERN_CANDIDATE_DELETE_ALL);
        Privilege commentCandidatePrivilegeAll = createPrivilegeIfNotFound(PrivilegeConstant.PATTERN_CANDIDATE_COMMENT_ALL);
        Privilege voteCandidatePrivilegeAll = createPrivilegeIfNotFound(PrivilegeConstant.PATTERN_CANDIDATE_VOTE_ALL);
        Privilege evidenceCandidatePrivilegeAll = createPrivilegeIfNotFound(PrivilegeConstant.PATTERN_CANDIDATE_EVIDENCE_ALL);
        Privilege toApprovedPatternAll = createPrivilegeIfNotFound(PrivilegeConstant.PATTERN_CANDIDATE_TO_PATTERN_ALL);
        /** Pattern Language*/
        Privilege readPatternLanguagePrivilege = createPrivilegeIfNotFound(PrivilegeConstant.PATTERN_LANGUAGE_READ);
        Privilege updatePatternLanguagePrivilege = createPrivilegeIfNotFound(PrivilegeConstant.PATTERN_LANGUAGE_EDIT);
        Privilege deletePatternLanguagePrivilege = createPrivilegeIfNotFound(PrivilegeConstant.PATTERN_LANGUAGE_DELETE);
        Privilege createPatternLanguagePrivilege = createPrivilegeIfNotFound(PrivilegeConstant.PATTERN_LANGUAGE_CREATE);
        Privilege readPatternLanguagePrivilegeAll = createPrivilegeIfNotFound(PrivilegeConstant.PATTERN_LANGUAGE_READ_ALL);
        Privilege updatePatternLanguagePrivilegeAll = createPrivilegeIfNotFound(PrivilegeConstant.PATTERN_LANGUAGE_EDIT_ALL);
        Privilege deletePatternLanguagePrivilegeAll = createPrivilegeIfNotFound(PrivilegeConstant.PATTERN_LANGUAGE_DELETE_ALL);
        /** Pattern */
        Privilege readPatternPrivilege      = createPrivilegeIfNotFound(PrivilegeConstant.APPROVED_PATTERN_READ);
        Privilege updatePatternPrivilege    = createPrivilegeIfNotFound(PrivilegeConstant.APPROVED_PATTERN_EDIT);
        Privilege deletePatternPrivilege    = createPrivilegeIfNotFound(PrivilegeConstant.APPROVED_PATTERN_DELETE);
        Privilege createPatternPrivilege    = createPrivilegeIfNotFound(PrivilegeConstant.APPROVED_PATTERN_CREATE);
        Privilege readPatternPrivilegeAll   = createPrivilegeIfNotFound(PrivilegeConstant.APPROVED_PATTERN_READ_ALL);
        Privilege updatePatternPrivilegeAll = createPrivilegeIfNotFound(PrivilegeConstant.APPROVED_PATTERN_EDIT_ALL);
        Privilege deletePatternPrivilegeAll = createPrivilegeIfNotFound(PrivilegeConstant.APPROVED_PATTERN_DELETE_ALL);
        /** USER */
        Privilege readUserPrivilege         = createPrivilegeIfNotFound(PrivilegeConstant.USER_READ);
        Privilege createUserPrivilege       = createPrivilegeIfNotFound(PrivilegeConstant.USER_CREATE);
        Privilege updateUserPrivilege       = createPrivilegeIfNotFound(PrivilegeConstant.USER_EDIT);
        Privilege deleteUserPrivilege       = createPrivilegeIfNotFound(PrivilegeConstant.USER_DELETE);
        Privilege readUserPrivilegeAll      = createPrivilegeIfNotFound(PrivilegeConstant.USER_READ_ALL);
        Privilege updateUserPrivilegeAll    = createPrivilegeIfNotFound(PrivilegeConstant.USER_EDIT_ALL);
        Privilege deleteUserPrivilegeAll    = createPrivilegeIfNotFound(PrivilegeConstant.USER_DELETE_ALL);
        Privilege userPrivilegeAll          = createPrivilegeIfNotFound(PrivilegeConstant.USER_ALL);

        /*this.patternLanguageRepository.findAll().stream().forEach(patternLanguage -> {
            Privilege readPatternLanguagePrivilegeRes = createPrivilegeIfNotFound(PrivilegeConstant.PATTERN_LANGUAGE_READ + '_' + patternLanguage.getId().toString());
            Privilege updatePatternLanguagePrivilegeRes = createPrivilegeIfNotFound(PrivilegeConstant.PATTERN_LANGUAGE_EDIT + '_' + patternLanguage.getId().toString());
            Privilege deletePatternLanguagePrivilegeRes = createPrivilegeIfNotFound(PrivilegeConstant.PATTERN_LANGUAGE_DELETE + '_' + patternLanguage.getId().toString());
            createRoleIfNotFound(RoleConstant.HELPER + "_PATTERN_LANGUAGE_" + patternLanguage.getId().toString(), Arrays.asList(
                readPatternLanguagePrivilegeRes, updatePatternLanguagePrivilegeRes
            ));
            createRoleIfNotFound(RoleConstant.MAINTAINER + "_PATTERN_LANGUAGE_" + patternLanguage.getId().toString(), Arrays.asList(
                readPatternLanguagePrivilegeRes, updatePatternLanguagePrivilegeRes, deletePatternLanguagePrivilegeRes
            ));
            createRoleIfNotFound(RoleConstant.OWNER + "_PATTERN_LANGUAGE_" + patternLanguage.getId().toString(), Arrays.asList(
                readPatternLanguagePrivilegeRes, updatePatternLanguagePrivilegeRes, deletePatternLanguagePrivilegeRes
            ));
        });

        this.patternRepository.findAll().stream().forEach(pattern -> {
            Privilege readPatternPrivilegeRes = createPrivilegeIfNotFound(PrivilegeConstant.APPROVED_PATTERN_READ + '_' + pattern.getId().toString());
            Privilege updatePatternPrivilegeRes = createPrivilegeIfNotFound(PrivilegeConstant.APPROVED_PATTERN_EDIT + '_' + pattern.getId().toString());
            Privilege deletePatternPrivilegeRes = createPrivilegeIfNotFound(PrivilegeConstant.APPROVED_PATTERN_DELETE + '_' + pattern.getId().toString());
            createRoleIfNotFound(RoleConstant.HELPER + "_APPROVED_PATTERN_" + pattern.getId().toString(), Arrays.asList(
                readPatternPrivilegeRes, updatePatternPrivilegeRes, deletePatternPrivilegeRes
            ));
            createRoleIfNotFound(RoleConstant.MAINTAINER + "_APPROVED_PATTERN_" + pattern.getId().toString(), Arrays.asList(
                readPatternPrivilegeRes, updatePatternPrivilegeRes, deletePatternPrivilegeRes
            ));
            createRoleIfNotFound(RoleConstant.OWNER + "_APPROVED_PATTERN_" + pattern.getId().toString(), Arrays.asList(
                readPatternPrivilegeRes, updatePatternPrivilegeRes, deletePatternPrivilegeRes
            ));
        });*/

        /** Roles */
        createRoleIfNotFound(RoleConstant.HELPER, Arrays.asList(
                //ISSUES
                readIssuePrivilege, 
                commentIssuePrivilege, voteIssuePrivilege, evidenceIssuePrivilege,
                //CANDIDATE
                readCandidatePrivilege, 
                commentCandidatePrivilege, voteCandidatePrivilege, evidenceCandidatePrivilege,
                //Pattern,
                readPatternPrivilege, updatePatternPrivilege,
                //USER
                readUserPrivilege, updateUserPrivilege, deleteUserPrivilege
        ));
        createRoleIfNotFound(RoleConstant.MAINTAINER, Arrays.asList(
                //ISSUES
                readIssuePrivilege, updateIssuePrivilege, 
                commentIssuePrivilege, voteIssuePrivilege, evidenceIssuePrivilege,
                //CANDIDATE
                readCandidatePrivilege, updateCandidatePrivilege, 
                commentCandidatePrivilege, voteCandidatePrivilege, evidenceCandidatePrivilege,
                //Pattern,
                readPatternPrivilege, updatePatternPrivilege, deletePatternPrivilege,
                //USER
                readUserPrivilege, updateUserPrivilege, deleteUserPrivilege
        ));
        createRoleIfNotFound(RoleConstant.OWNER, Arrays.asList(
                //ISSUES
                readIssuePrivilege, updateIssuePrivilege, deleteIssuePrivilege, toPatternCandidate, 
                commentIssuePrivilege, voteIssuePrivilege, evidenceIssuePrivilege,
                //CANDIDATE
                readCandidatePrivilege, updateCandidatePrivilege, deleteCandidatePrivilege, toApprovedPattern, 
                commentCandidatePrivilege, voteCandidatePrivilege, evidenceCandidatePrivilege,
                //Pattern,
                readPatternPrivilege, updatePatternPrivilege, deletePatternPrivilege,
                //USER
                readUserPrivilege, updateUserPrivilege, deleteUserPrivilege
        ));
        createRoleIfNotFound(RoleConstant.MEMBER, Arrays.asList(
                //ISSUES
                createIssuePrivilege, readIssuePrivilegeAll, 
                commentIssuePrivilegeAll, voteIssuePrivilegeAll, evidenceIssuePrivilegeAll,
                //CANDIDATE
                readCandidatePrivilegeAll, 
                commentCandidatePrivilegeAll, voteCandidatePrivilegeAll, evidenceCandidatePrivilegeAll,
                //Pattern Language,
                readPatternPrivilegeAll,
                //Pattern,
                readPatternPrivilegeAll,
                //USER
                readUserPrivilege, updateUserPrivilege, deleteUserPrivilege
        ));
        createRoleIfNotFound(RoleConstant.EXPERT, Arrays.asList(
                //ISSUES
                createIssuePrivilege, readIssuePrivilegeAll, updateIssuePrivilegeAll, deleteIssuePrivilegeAll, toPatternCandidateAll, 
                commentIssuePrivilegeAll, voteIssuePrivilegeAll, evidenceIssuePrivilegeAll,
                //CANDIDATE
                createCandidatePrivilege, readCandidatePrivilegeAll, updateCandidatePrivilegeAll, deleteCandidatePrivilegeAll, 
                commentCandidatePrivilegeAll, voteCandidatePrivilegeAll, evidenceCandidatePrivilegeAll,
                //PATTERN
                createPatternPrivilege, readPatternPrivilegeAll, updatePatternPrivilegeAll, deletePatternPrivilegeAll,
                //USER
                readUserPrivilege, updateUserPrivilege, deleteUserPrivilege
        ));
        createRoleIfNotFound(RoleConstant.LIBRARIAN, Arrays.asList(
                //ISSUES
                createIssuePrivilege, readIssuePrivilegeAll, updateIssuePrivilegeAll, deleteIssuePrivilegeAll, 
                commentIssuePrivilegeAll, voteIssuePrivilegeAll, evidenceIssuePrivilegeAll,
                //CANDIDATE
                createCandidatePrivilege, readCandidatePrivilegeAll, updateCandidatePrivilegeAll, deleteCandidatePrivilegeAll, 
                commentCandidatePrivilegeAll, voteCandidatePrivilegeAll, evidenceCandidatePrivilegeAll,
                //PATTERN
                createPatternPrivilege, readPatternPrivilegeAll, updatePatternPrivilegeAll, deletePatternPrivilegeAll,
                //USER
                readUserPrivilege, createUserPrivilege, updateUserPrivilege, deleteUserPrivilege, readUserPrivilegeAll, updateUserPrivilegeAll, deleteUserPrivilegeAll
        ));
        /*createRoleIfNotFound(RoleConstant.ADMIN, Arrays.asList(
                //ISSUES
                createIssuePrivilege, readIssuePrivilegeAll, updateIssuePrivilegeAll, deleteIssuePrivilegeAll, 
                commentIssuePrivilegeAll, voteIssuePrivilegeAll, evidenceIssuePrivilegeAll, toPatternCandidateAll,
                //CANDIDATE
                createCandidatePrivilege, readCandidatePrivilegeAll, updateCandidatePrivilegeAll, deleteCandidatePrivilegeAll,
                commentCandidatePrivilegeAll, voteCandidatePrivilegeAll, evidenceCandidatePrivilegeAll, toApprovedPatternAll,
                //PATTERN
                createPatternPrivilege, readPatternPrivilegeAll, updatePatternPrivilegeAll, deletePatternPrivilegeAll,
                //USER
                readUserPrivilege, createUserPrivilege, updateUserPrivilege, deleteUserPrivilege, readUserPrivilegeAll, updateUserPrivilegeAll, deleteUserPrivilegeAll, userPrivilegeAll
        ));*/

        createUser(new UserEntity("MEMBER", "member@mail", passwordEncoder.encode("pass"), Arrays.asList(roleRepository.findByName(RoleConstant.MEMBER))));
        createUser(new UserEntity("MEMBER_1", "member1@mail", passwordEncoder.encode("pass"), Arrays.asList(roleRepository.findByName(RoleConstant.MEMBER))));
        createUser(new UserEntity("EXPERT", "expert@mail", passwordEncoder.encode("pass"), Arrays.asList(roleRepository.findByName(RoleConstant.EXPERT))));
        createUser(new UserEntity("LIBRARIAN", "librarian@mail", passwordEncoder.encode("pass"), Arrays.asList(roleRepository.findByName(RoleConstant.LIBRARIAN))));
        //createUser(new UserEntity("ADMIN", "admin@mail", passwordEncoder.encode("pass"), Arrays.asList(roleRepository.findByName(RoleConstant.ADMIN))));

        alreadySetup = true;
    }

    @Transactional
    void createUser(UserEntity userEntity) {
            UserEntity user = userRepository.findByName(userEntity.getName());
        if (user == null)
            userRepository.save(userEntity);
    }

    @Transactional
    Role createRoleIfNotFound(String name, Collection<Privilege> privileges) {

        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
            role.setPrivileges(privileges);
            roleRepository.save(role);
        }
        return role;
    }

    @Transactional
    Privilege createPrivilegeIfNotFound(String name) {

        Privilege privilege = privilegeRepository.findByName(name);
        if (privilege == null) {
            privilege = new Privilege(name);
            privilegeRepository.save(privilege);
        }
        return privilege;
    }


}
