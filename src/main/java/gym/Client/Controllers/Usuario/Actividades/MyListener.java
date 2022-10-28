package gym.Client.Controllers.Usuario.Actividades;

import gym.Client.Classes.ActividadObject;
import gym.Client.Classes.EmpleadoObject;

public interface MyListener {

    public void onClickActividad(ActividadObject actividadObject);

    public void onClickUsuario(EmpleadoObject empleadoObject);
}
