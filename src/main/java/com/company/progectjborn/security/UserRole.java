package com.company.progectjborn.security;

import com.company.progectjborn.entity.BankAccount;
import com.company.progectjborn.entity.Client;
import com.company.progectjborn.entity.Transaction;
import com.company.progectjborn.entity.Type;
import io.jmix.security.model.EntityAttributePolicyAction;
import io.jmix.security.model.EntityPolicyAction;
import io.jmix.security.role.annotation.EntityAttributePolicy;
import io.jmix.security.role.annotation.EntityPolicy;
import io.jmix.security.role.annotation.ResourceRole;
import io.jmix.securityui.role.annotation.MenuPolicy;
import io.jmix.securityui.role.annotation.ScreenPolicy;

@ResourceRole(name = "UserRole", code = "user-role")
public interface UserRole {
    @EntityAttributePolicy(entityClass = BankAccount.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = BankAccount.class, actions = EntityPolicyAction.ALL)
    void bankAccount();

    @EntityAttributePolicy(entityClass = Client.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Client.class, actions = EntityPolicyAction.ALL)
    void client();

    @EntityAttributePolicy(entityClass = Transaction.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Transaction.class, actions = EntityPolicyAction.ALL)
    void transaction();

    @EntityAttributePolicy(entityClass = Type.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Type.class, actions = EntityPolicyAction.ALL)
    void type();

    @MenuPolicy(menuIds = {"Type_.browse", "BankAccount.browse", "Transaction_.browse"})
    @ScreenPolicy(screenIds = {"bulkEditorWindow", "ui_LayoutAnalyzerScreen", "singleFileUploadDialog", "ui_AddConditionScreen", "ui_JpqlFilterCondition.edit", "ui_PropertyFilterCondition.edit", "ui_GroupFilterCondition.edit", "ui_FilterConfigurationModel.fragment", "ui_MBeanInspectScreen", "ui_MBeanAttribute.edit", "ui_MBeanOperationResultScreen", "ui_MBeanOperationFragment", "notFoundScreen", "inputDialog", "selectValueDialog", "backgroundWorkProgressScreen", "ui_DateIntervalDialog", "ui_UiDataFilterConfigurationModel.fragment", "sec_RoleFilterFragment", "sec_ResourceRoleModel.lookup", "sec_ResourceRoleModel.edit", "sec_RowLevelRoleModel.edit", "sec_RowLevelRoleModel.lookup", "ResetPasswordDialog", "ChangePasswordDialog", "sec_MenuResourcePolicyModel.edit", "sec_ScreenResourcePolicyModel.edit", "sec_ScreenResourcePolicyModel.create", "sec_MenuResourcePolicyModel.create", "sec_EntityResourcePolicyModel.create", "sec_EntityAttributeResourcePolicyModel.create", "sec_EntityAttributeResourcePolicyModel.edit", "sec_GraphQLResourcePolicyModel.edit", "sec_EntityResourcePolicyModel.edit", "sec_ResourcePolicyModel.edit", "sec_SpecificResourcePolicyModel.edit", "sec_RoleAssignmentScreen", "sec_RoleAssignmentFragment", "sec_RowLevelPolicyModel.edit", "sec_UserSubstitutionEntity.edit", "sec_UserSubstitutionsFragment", "sec_UserSubstitutionsScreen", "entityInfoWindow", "entityInspector.edit", "MainScreen", "User.edit", "LoginScreen", "Type_.browse", "Type_.edit", "BankAccount.browse", "BankAccount.edit", "Transaction_.browse"})
    void screens();
}