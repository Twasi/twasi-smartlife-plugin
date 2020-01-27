package net.twasiplugin.smartlife;

import net.twasi.core.database.models.User;
import net.twasi.core.interfaces.api.TwasiInterface;
import net.twasi.core.models.Message.TwasiMessage;
import net.twasi.core.plugin.api.TwasiUserPlugin;
import net.twasi.core.plugin.api.events.TwasiEnableEvent;
import net.twasi.core.plugin.api.variables.TwasiVariableBase;
import net.twasi.core.services.ServiceRegistry;
import net.twasidependency.smartlife.services.SmarthomeSequenceService;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SmartlifeUserplugin extends TwasiUserPlugin {

    private User user;

    @Override
    public void onEnable(TwasiEnableEvent e) {
        this.user = e.getUserPlugin().getTwasiInterface().getStreamer().getUser();
    }

    @Override
    public List<TwasiVariableBase> getVariables() {
        SmarthomeSequenceService service = ServiceRegistry.get(SmarthomeSequenceService.class);
        return service
                .getSceneSequenceVariables(user)
                .stream()
                .map(var -> new TwasiVariableBase(this) {
                    @Override
                    public List<String> getNames() {
                        return Collections.singletonList(var);
                    }

                    @Override
                    public String process(String name, TwasiInterface inf, String[] params, TwasiMessage message) {
                        service.forceRunSequence(user, service.getSequenceByVariable(user, var));
                        return "";
                    }
                }).collect(Collectors.toList());
    }
}
