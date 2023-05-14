import argparse
import subprocess
import sys


def install(package):
    subprocess.check_call([sys.executable, "-m", "pip", "install", package])


try:
    import toml
    import yaml
except ImportError:
    install("toml")
    install("pyyaml")
    import toml
    import yaml


def toml_to_yaml(toml_file_path, yaml_file_path):
    # Load TOML file
    with open(toml_file_path, 'r') as toml_file:
        toml_data = toml.load(toml_file)

    # Convert to YAML
    with open(yaml_file_path, 'w+') as yaml_file:
        yaml.dump(toml_data, yaml_file)


# Create the argument parser
parser = argparse.ArgumentParser(description='Convert TOML file to YAML file.')
parser.add_argument('toml_file', help='Path to the input TOML file')
parser.add_argument('yaml_file', help='Path to the output YAML file')

# Parse the arguments
args = parser.parse_args()

# Call the conversion function
toml_to_yaml(args.toml_file, args.yaml_file)
