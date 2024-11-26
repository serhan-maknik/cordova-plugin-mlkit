package cordova.mlkit.plugin;

import android.Manifest;
import android.app.Application;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import androidx.core.content.ContextCompat;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import cordova.mlkit.plugin.BarcodeScanningFragment;


import java.util.List;

/**
 * This class echoes a string called from JavaScript.
 */
public class MLKitPlugin extends CordovaPlugin {
    private Application app;
    private String package_name;
    private Resources resources;

    private static final int CAMERA_PERMISSION_REQUEST_CODE = 101;

    CallbackContext callbackContext;
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        this.callbackContext = callbackContext;
        app = cordova.getActivity().getApplication();
        package_name = app.getPackageName();
        resources = app.getResources();
        if (action.equals("openMLKit")) {
            String message = args.getString(0);
            if (ContextCompat.checkSelfPermission(cordova.getActivity(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                showBarcodeScannerDialog(resources, package_name);
            } else {
                cordova.requestPermissions(this, CAMERA_PERMISSION_REQUEST_CODE,
                        new String[]{android.Manifest.permission.CAMERA});
            }

            return true;
        }
        return false;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

    }
    @Override
    public void onRequestPermissionResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                showBarcodeScannerDialog(resources, package_name);
            } else {
                callbackContext.error("Camere permission denied");
            }
        }
    }
    private void showBarcodeScannerDialog(Resources resources, String package_name) {
        cordova.getActivity().runOnUiThread(() -> {
            BarcodeScanningFragment dialog = new BarcodeScanningFragment(resources, package_name);
            dialog.setListener(new BarcodeScanningFragment.BarcodeScanningInterface() {
                @Override
                public void onResult(List<String> codes) {
                    callbackContext.success(codes.get(0));
                    dialog.dismiss();
                }
            });
            dialog.show((cordova.getActivity()).getSupportFragmentManager(), dialog.getClass().getName());
        });
    }
}
